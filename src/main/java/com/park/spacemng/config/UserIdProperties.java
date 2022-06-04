package com.park.spacemng.config;

import lombok.Getter;
import lombok.Setter;

import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties(prefix = "user-id")
public class UserIdProperties {

	private int length;

}