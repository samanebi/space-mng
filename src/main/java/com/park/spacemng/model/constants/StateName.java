package com.park.spacemng.model.constants;

import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StateName {

	//@formatter off
	TEHRAN(0);
	//@formatter on

	private final int value;

	@JsonCreator
	public static StateName fromValue(int value) {
		return Stream.of(StateName.values()).filter(state -> state.value == value).findFirst()
				.orElseThrow(() -> new IllegalArgumentException("invalid geo state found: " + value));
	}

	@JsonValue
	public int toValue() {
		return value;
	}

}