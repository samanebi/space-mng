package com.park.spacemng.service.redis.impl;

import com.park.spacemng.model.user.User;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OnlineUserRedisDao extends AbstractRedisDaoImpl<String, User> {

	private static final String ONLINE_USER_REDIS_DOA_HEADER = "ONLINE_USER";

	public OnlineUserRedisDao(RedisTemplate<String, User> redisTemplate) {
		super(redisTemplate, 1);
	}

	@Override
	protected String getKeyHeader() {
		return ONLINE_USER_REDIS_DOA_HEADER;
	}

}