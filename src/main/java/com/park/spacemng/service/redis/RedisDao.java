package com.park.spacemng.service.redis;

import java.time.Duration;

public interface RedisDao<M, T> {
	void set(M id, T value);

	void set(M id, T value, Duration timeout);

	void setOrUpdate(M id, T value);

	void setOrUpdate(M id, T value, Duration timeout);

	T get(M id);

	boolean exists(M id);

	void delete(M id);

	long getCount(M id);
}