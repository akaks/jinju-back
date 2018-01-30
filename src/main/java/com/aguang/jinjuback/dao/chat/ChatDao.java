package com.aguang.jinjuback.dao.chat;

import com.aguang.jinjuback.pojo.chat.ChatMessage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ChatDao {

	@Insert("INSERT INTO `jj_vote`(jinju_id, user_id, type, create_time) VALUES (#{jijuId}, #{userId}, #{type}, #{currentTime})")
	Integer createChatMessage(ChatMessage chatMessage);


//private String id;
//private String userId;
//private String username;
//private String photoUrl;
//private String message;
//private Long createTime;
///* 消息类型:  1：聊天消息  2：进入、退出提示消息 */
//private String type;
}
