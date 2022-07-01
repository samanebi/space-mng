package com.park.spacemng.model.constants;

import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ProcessStatus {

	//@formatter off
	SUCCESS(0),
	FAILURE(1),
	INTERNAL(500);
	//@formatter on

	private final int value;

	@JsonCreator
	public static ProcessStatus fromValue(int value) {
		return Stream.of(ProcessStatus.values()).filter(status -> status.value == value).findFirst()
				.orElseThrow(() -> new IllegalArgumentException("invalid status found: " + value));
	}

	@JsonValue
	public int toValue() {
		return value;
	}

}