package com.park.spacemng.config;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(value = "parameter.validation.messages")
public class ParameterValidationMessageProperties {

	private final String nullParameter;

	private final String blankParameter;

	private final String equalParameter;

}