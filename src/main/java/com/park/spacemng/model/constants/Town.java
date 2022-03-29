package com.park.spacemng.model.constants;

import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Town {

	//@formatter off
	TEHRAN(0);
	//@formatter on

	private final int value;

	@JsonCreator
	public static Town fromValue(int value) {
		return Stream.of(Town.values()).filter(town -> town.value == value).findFirst()
				.orElseThrow(() -> new IllegalArgumentException("invalid town found: " + value));
	}

	@JsonValue
	public int toValue() {
		return value;
	}

}