package com.park.spacemng.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.MongoTemplate;

@EnableMongoAuditing
@Configuration
public class MongoConfig {

	@Bean
	public MongoTemplate mongoTemplate(MongoDatabaseFactory mongoDbFactory) {
		return new MongoTemplate(mongoDbFactory);
	}

}