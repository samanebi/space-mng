package com.park.spacemng.config;

import lombok.Getter;
import lombok.Setter;

import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties(value = "parameter.validation.messages")
public class ParameterValidationMessageProperties {

	private String nullParameter;

	private String blankParameter;

	private String equalParameter;

}