package com.park.spacemng.service.redis.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OnlineUserRedisDao extends AbstractRedisDaoImpl<String, Long> {

	private static final String ONLINE_USER_REDIS_DAO_HEADER = "ONLINE_USER";

	public OnlineUserRedisDao(RedisTemplate<String, Long> redisTemplate, @Value("${online.user.ttl}") int ttlHours) {
		super(redisTemplate, ttlHours);
	}

	@Override
	protected String getKeyHeader() {
		return ONLINE_USER_REDIS_DAO_HEADER;
	}

}