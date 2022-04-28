package com.park.spacemng.service.redis.impl;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import com.park.spacemng.service.redis.RedisDao;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.redis.core.RedisTemplate;

@Slf4j
public abstract class AbstractRedisDaoImpl<M, T> implements RedisDao<M, T> {

	protected final RedisTemplate<String, T> redisTemplate;

	protected final int ttl;

	protected TimeUnit timeUnit = TimeUnit.HOURS;

	public AbstractRedisDaoImpl(RedisTemplate<String, T> redisTemplate, int ttl) {
		this.redisTemplate = redisTemplate;
		this.ttl = ttl;
	}

	@Override
	public void set(M id, T value) {
		final String key = getKey(id);
		delete(id);
		redisTemplate.opsForValue().set(key, value, ttl, getTimeUnit());
	}

	@Override
	public void set(M id, T value, Duration timeout) {
		final String key = getKey(id);
		delete(id);
		redisTemplate.opsForValue().set(key, value, timeout);
	}

	@Override
	public void setOrUpdate(M id, T value) {
		final String key = getKey(id);
		redisTemplate.opsForValue().set(key, value, ttl, getTimeUnit());
	}

	@Override
	public void setOrUpdate(M id, T value, Duration timeout) {
		final String key = getKey(id);
		redisTemplate.opsForValue().set(key, value, timeout);
	}

	@Override
	public T get(M id) {
		final String key = getKey(id);
		if (!exists(id)) {
			return null;
		}
		return redisTemplate.opsForValue().get(key);
	}

	@Override
	public boolean exists(M id) {
		final String key = getKey(id);
		Boolean result = redisTemplate.hasKey(key);
		return result != null ? result : false;
	}

	@Override
	public void delete(M id) {
		final String key = getKey(id);
		redisTemplate.delete(key);
	}

	@Override
	public long getCount(final M id) {
		final String key = getKey(id);
		Long listSize = redisTemplate.opsForList().size(key);
		if (listSize == null) {
			listSize = 0L;
		}
		return listSize;
	}

	protected abstract String getKeyHeader();

	protected TimeUnit getTimeUnit() {
		return timeUnit;
	}

	protected String getKey(final M id) {
		return getKeyHeader() + "_" + id;
	}

}