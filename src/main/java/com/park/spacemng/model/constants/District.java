package com.park.spacemng.model.constants;

import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum District {

	//@formatter off
	ONE(1),
	TWO(2),
	THREE(3),
	FOUR(4),
	FIVE(5),
	SIX(6),
	SEVEN(7),
	EIGHT(8),
	NINE(9),
	TEN(10),
	ELEVEN(11),
	TWELVE(12);
	//@formatter on

	private final int value;

	@JsonCreator
	public static District fromValue(int value) {
		return Stream.of(District.values()).filter(district -> district.value == value).findFirst()
				.orElseThrow(() -> new IllegalArgumentException("invalid geo district found: " + value));
	}

	@JsonValue
	public int toValue() {
		return value;
	}

}