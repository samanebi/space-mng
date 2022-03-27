package com.park.spacemng.model.user.constants;

import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CarType {

	MICRO(0), SEDAN(1), TRUCK(2), VAN(3), COUPE(4);

	private final int value;

	@JsonCreator
	public static CarType fromValue(int value) {
		return Stream.of(CarType.values()).filter(type -> type.value == value).findFirst()
				.orElseThrow(() -> new IllegalArgumentException("invalid car type found: " + value));
	}

	@JsonValue
	public int toValue() {
		return value;
	}

}