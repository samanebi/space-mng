package com.park.spacemng.config;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@Configuration
@RequiredArgsConstructor
public class RedisConfig {

	@Bean(name = "RedisTemplate")
	public RedisTemplate<String, Long> redisTemplate(
			RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, Long> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory);
		template.setKeySerializer(new GenericToStringSerializer<>(String.class));
		template.setValueSerializer(new Jackson2JsonRedisSerializer<>(Long.class));
		return template;
	}

}