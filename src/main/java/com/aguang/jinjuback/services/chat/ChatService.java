package com.aguang.jinjuback.services.chat;

import com.aguang.jinjuback.configuration.CustomException;
import com.aguang.jinjuback.dao.chat.ChatDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

@Service
public class ChatService {
	public final static Logger logger = LoggerFactory.getLogger(ChatService.class);

	/* 游客id */
	public static Integer visitorId = 10000;

	@Autowired
	private JedisPool jedisPool;

	@Autowired
	private ChatDao chatDao;

	/**
	 * 获取下一个id
	 * @return
	 */
	public Integer nextVisitorId() {

		if(visitorId == 99999) {
			visitorId = 10000;
		} else {
			visitorId++;
		}
		return visitorId;
	}

	public List<String> getHistoryMessage(Integer pageIndex, Integer pageSize) {

		Integer start = (pageIndex - 1) * pageSize;
		Integer end = pageIndex * pageSize - 1;

		Jedis jedis = null;
		List<String> chatMessages = null;

		try {
			jedis = jedisPool.getResource();
			chatMessages = jedis.lrange("chat", start, end);
		} catch (Exception ex) {
			logger.error("错误", ex);
			throw new CustomException("Redis连接错误");
		} finally {
			if (jedis != null) {
				//关闭连接
				jedis.close();
			}
		}

		return chatMessages;
	}
}
