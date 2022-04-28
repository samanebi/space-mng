package com.park.spacemng.config;

import com.park.spacemng.model.user.User;
import lombok.RequiredArgsConstructor;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@Configuration
@RequiredArgsConstructor
public class RedisConfig {

	private final Environment env;

	@Bean
	@ConditionalOnProperty(value = "redis.single.node", havingValue = "true")
	public JedisConnectionFactory singleNodeJedisConnectionFactory() {
		String host = this.env.getRequiredProperty("spring.redis.host");
		Integer port = this.env.getRequiredProperty("spring.redis.port", Integer.class);
		return new JedisConnectionFactory(new RedisStandaloneConfiguration(host, port));
	}

	@Bean(name = "OnlineUserRedisTemplate")
	public RedisTemplate<String, User> leaderBoardInfoRedisTemplate(
			JedisConnectionFactory jedisConnectionFactory) {
		final RedisTemplate<String, User> template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory);
		template.setKeySerializer(new GenericToStringSerializer<>(String.class));
		template.setValueSerializer(new Jackson2JsonRedisSerializer<>(User.class));
		return template;
	}

}