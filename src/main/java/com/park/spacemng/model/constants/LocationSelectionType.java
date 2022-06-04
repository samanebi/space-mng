package com.park.spacemng.model.constants;

import java.util.Arrays;
import java.util.Objects;

import lombok.Getter;

@Getter
public enum LocationSelectionType {

	DISTRICT_BASED(0), GEOGRAPHIC(1);

	private final int value;

	LocationSelectionType(Integer value) {
		this.value = value;
	}

	public static LocationSelectionType fromValue(Integer value) {
		return Arrays.stream(LocationSelectionType.values())
				.filter(type -> Objects.equals(type.getValue(), value))
				.findFirst().orElseThrow(() ->
						new IllegalArgumentException("location selection type not found " + value));
	}

}