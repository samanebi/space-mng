package com.park.spacemng.model.constants;

import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Gender {

	MALE(0), FEMALE(1);

	private final int value;

	@JsonCreator
	public static Gender fromValue(int value) {
		return Stream.of(Gender.values()).filter(gender -> gender.value == value).findFirst()
				.orElseThrow(() -> new IllegalArgumentException("invalid gender found: " + value));
	}

	@JsonValue
	public int toValue() {
		return value;
	}

}