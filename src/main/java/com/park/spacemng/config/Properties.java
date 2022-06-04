package com.park.spacemng.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({ LocationSelectionProperties.class, TrackingCodeProperties.class,
		ParameterValidationMessageProperties.class, OnlineUserProperties.class })
public class Properties {

	// TODO : add properties
}