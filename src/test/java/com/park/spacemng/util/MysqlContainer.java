package com.park.spacemng.util;

import org.testcontainers.containers.MySQLContainer;

public class MysqlContainer extends MySQLContainer<MysqlContainer> {

	private static final String IMAGE_VERSION = "mysql:8.0.28-oracle";

	private static MysqlContainer container;

	private MysqlContainer() {
		super(IMAGE_VERSION);
	}

	public static MysqlContainer getInstance() {
		if (container == null) {
			container = new MysqlContainer();
		}
		return container;
	}

	@Override
	public void start() {
		super.start();
		System.setProperty("MYSQL-ADMIN-PASSWORD", container.getPassword());
		System.setProperty("MYSQL-ADMIN-USERNAME", container.getUsername());
		System.setProperty("MYSQL-CONTAINER-URL", container.getHost());
	}

}