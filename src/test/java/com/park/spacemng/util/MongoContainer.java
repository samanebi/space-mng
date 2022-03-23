package com.park.spacemng.util;

import org.testcontainers.containers.MongoDBContainer;

public class MongoContainer extends MongoDBContainer {

	private static final String IMAGE_VERSION = "mongo:5.0.6";

	private static MongoContainer container;

	private MongoContainer() {
		super(IMAGE_VERSION);
	}

	public static MongoContainer getInstance() {
		if (container == null) {
			container = new MongoContainer();
		}
		return container;
	}

	@Override
	public void start() {
		super.start();
		System.setProperty("MONGO-CONTAINER-URL", container.getReplicaSetUrl());
	}

}