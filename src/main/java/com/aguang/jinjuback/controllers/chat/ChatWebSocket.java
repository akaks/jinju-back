package com.aguang.jinjuback.controllers.chat;

import com.aguang.jinjuback.model.constants.ChatMessageTypeConstant;
import com.aguang.jinjuback.model.po.User;
import com.aguang.jinjuback.model.pojo.chat.ChatMessage;
import com.aguang.jinjuback.model.pojo.chat.ChatUser;
import com.aguang.jinjuback.services.UserService;
import com.aguang.jinjuback.services.chat.ChatService;
import com.aguang.jinjuback.utils.DateUtils;
import com.aguang.jinjuback.utils.SpringUtils;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/chatsocket/{userId}")
@Component
public class ChatWebSocket {

    public static final String QINIU = "http://p2g5cb64g.bkt.clouddn.com/";

    public static final String[] VISITOR_PHOTO =
            new String[]{"photo2.jpeg",
                    "photo1.jpeg",
                    "photo0.jpeg",
                    "dad129abd9170a9f!400x400_big.jpg",
                    "d87aa2da62deb3a0!400x400_big (1).png",
                    "a76aef6e4ff55a74!400x400_big.jpg",
                    "67f726b026ec2cf4!400x400_big.jpg",
                    "1550c2c786d68c2e!400x400_big.jpg",
                    "7a349c934a17270e!400x400_big.jpg",
                    "FqzcOdtAM96-Fe3ssizwDpMVCbiD",
    };

//    @Autowired
//    private UserService userService;

    // 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    public static int ONLINE_COUNT = 0;

    // 存放userId和Session
    public static ConcurrentHashMap<String, Session> webSocketMap = new ConcurrentHashMap<>();
    // 存放userId和用户信息
    public static ConcurrentHashMap<String, ChatUser> chatUserMap = new ConcurrentHashMap<>();

    /**
     * 连接打开的方法
     * @param session
     * @param userId
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {

        webSocketMap.put(userId, session);
        addOnlineCount(); //在线数加

        User user = getUserById(userId);
        /* 缓存当前用户信息 */
        ChatUser chatUser = new ChatUser();
        chatUser.setUserId(userId);
        // 判断是登录用户还是游客
        if(user == null) {
            chatUser.setIsVisitor(true);
            chatUser.setUsername("游客" + userId);
            chatUser.setPhotoUrl(getVisitorPhoto(userId));
        } else {
            chatUser.setIsVisitor(false);
            chatUser.setUsername(user.getUsername());
            chatUser.setPhotoUrl(user.getPhotoUrl());
        }
        chatUserMap.put(userId, chatUser);

        String message = "游客%s 进入了聊天室...";
        ChatMessage chatMessage = createChatMessage(userId, ChatMessageTypeConstant.TIP, message);

        // 将消息保存到数据库
        saveMessageToDB(chatMessage);

        String chatMessageJson = JSON.toJSONString(chatMessage);
        sendMassMessage(chatMessageJson);

        System.out.println(userId + "进入聊天室!");
        System.out.println("###有新连接加入! 当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);
        //获取用户ID
        Map<String, String> map = session.getPathParameters();

        String userId = map.get("userId");

        ChatMessage chatMessage = createChatMessage(userId, ChatMessageTypeConstant.DIALOGUE, message);

        // 将消息保存到数据库
        saveMessageToDB(chatMessage);

        // 将消息对象转换出json
        String chatMessageJson = JSON.toJSONString(chatMessage);

        // 群发信息
        sendMassMessage(chatMessageJson);
    }

    /**
     * 连接关闭调用的方法
     * @param session
     */
    @OnClose
    public void onClose(Session session) {
        Map<String, String> map = session.getPathParameters();
        webSocketMap.remove(map.get("userId")); //从set中删除
        chatUserMap.remove(map.get("userId")); //从set中删除

        subOnlineCount(); //在线数减 1

        String userId = map.get("userId");

        String message = "游客%s 离开了聊天室...";
        ChatMessage chatMessage = createChatMessage(userId, ChatMessageTypeConstant.TIP, message);

        // 将消息保存到数据库
        saveMessageToDB(chatMessage);

        String chatMessageJson = JSON.toJSONString(chatMessage);
        // 群发消息
        sendMassMessage(chatMessageJson);

        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());

    }

    /**
     * 发生错误时调用
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }

    /**
     * 创建消息
     * @param userId 用户id
     * @param type 消息类型
     * @param message 消息
     * @return
     */
    private ChatMessage createChatMessage(String userId, Integer type, String message) {

        User user = null;
        if(new Integer(userId) < 10000) {
            user = getUserById(userId);
        }

        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setUserId(userId);
        // 设置消息类型为： 提示消息
        chatMessage.setType(type);
        chatMessage.setCreateTime(DateUtils.getCurrentTime());

        // 判断是否需要显示时间
        if(ChatMessageTypeConstant.DIALOGUE.equals(type) && isNeedShowTime()) {
            chatMessage.setIsShowTime(true);
        } else {
            chatMessage.setIsShowTime(false);
        }

        // 判断是登录用户还是游客
        if(user == null) {
            chatMessage.setIsVisitor(true);
            chatMessage.setUsername("游客" + userId);
            chatMessage.setPhotoUrl(getVisitorPhoto(userId));
            if(ChatMessageTypeConstant.DIALOGUE.equals(type)) {
                chatMessage.setMessage(message);
            } else {
                chatMessage.setMessage(String.format(message, userId));
            }
        } else {
            chatMessage.setIsVisitor(false);
            chatMessage.setUsername(user.getUsername());
            chatMessage.setPhotoUrl(user.getPhotoUrl());
            if(ChatMessageTypeConstant.DIALOGUE.equals(type)) {
                chatMessage.setMessage(message);
            } else {
                chatMessage.setMessage(String.format(message, user.getUsername()));
            }
        }

        // 有用户进入或者由用户退出时，刷新User列表
        if(ChatMessageTypeConstant.TIP.equals(type)) {
            chatMessage.setUserList(getChatUserList());
        }

        return chatMessage;
    }

    /**
     * 发送消息
     * @param message
     * @param session
     * @throws IOException
     */
    public void sendMessage(String message, Session session) throws IOException {
        if (session.isOpen()) {
            session.getAsyncRemote().sendText(message);
        }
    }

    /**
     * 群发信息
     * @param message
     * @throws IOException
     */
    public void sendMassMessage(String message) {
        for (String user : webSocketMap.keySet()) {
            try {
                sendMessage(message, webSocketMap.get(user));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return ONLINE_COUNT;
    }

    public static synchronized void addOnlineCount() {
        ChatWebSocket.ONLINE_COUNT++;
    }

    public static synchronized void subOnlineCount() {
        ChatWebSocket.ONLINE_COUNT--;
    }

    /**
     * 获取用户对象
     * @param userId
     * @return
     */
    private User getUserById(String userId) {
        UserService userService = SpringUtils.getBean(UserService.class);

        try {
            return userService.getUserById(new Integer(userId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取聊天室中的用户列表
     * @return
     */
    private List<ChatUser> getChatUserList() {
        List<ChatUser> userList = new ArrayList<>();

        for (String userId : webSocketMap.keySet()) {
            ChatUser chatUser = chatUserMap.get(userId);
            userList.add(chatUser);
        }

        return userList;
    }

    /**
     * 保存消息至redis
     * @param message
     */
    @Deprecated
    private void saveToRedis(String message) {
        JedisPool jedisPool = SpringUtils.getBean("jedisPool");

        Jedis jedis = null;

        try {
            jedis = jedisPool.getResource();

            jedis.lpush("chat", message);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (jedis != null) {
                //关闭连接
                jedis.close();
            }
        }
    }

    /**
     * 保存消息至数据库
     * @param chatMessage
     * @return
     */
    private Integer saveMessageToDB(ChatMessage chatMessage) {
        ChatService chatService = SpringUtils.getBean(ChatService.class);
        return chatService.createChatMessage(chatMessage);
    }

    /**
     * 判断是否需要显示时间
     * @return
     */
    private boolean isNeedShowTime() {
        ChatService chatService = SpringUtils.getBean(ChatService.class);

        try {
            return chatService.isNeedShowTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取游客头像图片
     * @param userId
     * @return
     */
    private String getVisitorPhoto(String userId) {
        return QINIU + VISITOR_PHOTO[userId.hashCode() % (VISITOR_PHOTO.length) ];
    }

}