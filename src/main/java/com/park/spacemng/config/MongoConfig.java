package com.park.spacemng.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeospatialIndex;

@EnableMongoAuditing
@Configuration
public class MongoConfig {

	@Bean
	public MongoTemplate mongoTemplate(MongoDatabaseFactory mongoDbFactory) {
		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory);
		mongoTemplate.indexOps(GeoJsonPoint.class).ensureIndex(new GeospatialIndex("position"));
		return mongoTemplate;
	}

}