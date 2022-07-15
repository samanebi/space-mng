package com.park.spacemng.util;

import com.redis.testcontainers.RedisContainer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("test")
@ExtendWith({ SpringExtension.class })
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
@Testcontainers
public abstract class AbstractBaseIntegrationTest {

	private final static int REDIS_DEFAULT_PORT = 6379;

	private final static String MONGO_DATABASE_NAME = "space-mng";

	private final static String MONGODB_IMAGE_TAG = "mongo:3.6.3";

	private final static String REDIS_IMAGE_TAG = "redis:4-alpine";

	public static MongoDBContainer mongoDBContainer = new MongoDBContainer(MONGODB_IMAGE_TAG).withReuse(true);

	public static RedisContainer redisContainer = new RedisContainer(DockerImageName.parse(REDIS_IMAGE_TAG))
			.withReuse(true);

	@LocalServerPort
	int port;

	@Autowired
	public TestRestTemplate restTemplate;

	public String getBaseUrl() {
		return "http://localhost:" + port + "/space-mng/api/";
	}

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
		registry.add("spring.redis.url", () -> redisContainer.getContainerIpAddress());
		registry.add("spring.redis.port", () -> redisContainer.getMappedPort(REDIS_DEFAULT_PORT));
		registry.add("spring.data.mongodb.database", () -> MONGO_DATABASE_NAME);
	}

	@BeforeAll
	public static void beforeAll() {
		mongoDBContainer.start();
		redisContainer.start();
	}

}