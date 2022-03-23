package com.park.spacemng.model.constants;

import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum RequestResolution {

	APPROVE(0), REJECT(1);

	private final int value;

	@JsonCreator
	public static RequestResolution fromValue(int value) {
		return Stream.of(RequestResolution.values()).filter(resolution -> resolution.value == value).findFirst()
				.orElseThrow(() -> new IllegalArgumentException("invalid resolution found: " + value));
	}

	@JsonValue
	public int toValue() {
		return value;
	}

}