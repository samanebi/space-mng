package com.park.spacemng.util;

import org.junit.ClassRule;
import org.testcontainers.containers.MongoDBContainer;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public abstract class AbstractBaseIntegrationTest {

	@ClassRule
	public static MongoDBContainer mongoDBContainer = MongoContainer.getInstance();

	@ClassRule
	public static MysqlContainer mysqlContainer = MysqlContainer.getInstance();

}