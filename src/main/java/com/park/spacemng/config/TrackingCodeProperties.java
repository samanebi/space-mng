package com.park.spacemng.config;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "tracking-code")
public class TrackingCodeProperties {

	private int length;
	
}