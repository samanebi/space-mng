package com.park.spacemng.model.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

import java.util.stream.Stream;

@AllArgsConstructor
public enum CarType {

    HUNCHBACK(0), SEDAN(1), SUV(2), LARGE(3);

    private final int value;

    @JsonCreator
    public static CarType fromValue(int value) {
        return Stream.of(CarType.values()).filter(carType -> carType.value == value).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("invalid car type found: " + value));
    }

    @JsonValue
    public int toValue() {
        return value;
    }

}
