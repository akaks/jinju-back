package com.aguang.jinjuback.test.distributedlock2;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.exceptions.JedisException;

import java.util.List;
import java.util.UUID;

/**
 * 分布式锁的简单实现代码
 * Created by liuyang on 2017/4/20.
 */
public class DistributedLock {

	private final JedisPool jedisPool;

	public DistributedLock(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

	/**
	 * 加锁
	 * @param lockName       锁的key
	 * @return 锁标识
	 */
	public String lock(String lockName) {
		Jedis conn = null;
		String retIdentifier = null;
		try {
			// 获取连接
			conn = jedisPool.getResource();
			// 随机生成一个value
			String identifier = UUID.randomUUID().toString();
			// 锁名，即key值
			String lockKey = "lock:" + lockName;
			// 超时时间，上锁后超过此时间则自动释放锁

			// 获取锁的超时时间，超过这个时间则放弃获取锁
			while (true) {
				if (conn.setnx(lockKey, identifier) == 1) {
					retIdentifier = identifier;
					return retIdentifier;
				}

				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		} catch (JedisException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return retIdentifier;
	}

	/**
	 * 释放锁
	 * @param lockName   锁的key
	 * @param identifier 释放锁的标识
	 * @return
	 */
	public boolean releaseLock(String lockName, String identifier) {
		Jedis conn = null;
		String lockKey = "lock:" + lockName;
		boolean retFlag = false;
		try {
			conn = jedisPool.getResource();
			while (true) {
				// 监视lock，准备开始事务
				conn.watch(lockKey);
				// 通过前面返回的value值判断是不是该锁，若是该锁，则删除，释放锁
				if (identifier.equals(conn.get(lockKey))) {
					Transaction transaction = conn.multi();
					transaction.del(lockKey);
					List<Object> results = transaction.exec();
					if (results == null) {
						continue;
					}
					retFlag = true;
				}
				conn.unwatch();
				break;
			}
		} catch (JedisException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return retFlag;
	}
}