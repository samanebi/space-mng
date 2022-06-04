package com.park.spacemng.config;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(value = "online.users")
public class OnlineUserProperties {

	private int ttl;

	private long timout;

}