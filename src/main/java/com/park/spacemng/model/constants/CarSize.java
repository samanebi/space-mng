package com.park.spacemng.model.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

import java.util.stream.Stream;

@AllArgsConstructor
public enum CarSize {

    HUNCHBACK(0), SEDAN(1), SUV(2), LARGE(3);

    private final int value;

    @JsonCreator
    public static CarSize fromValue(int value) {
        return Stream.of(CarSize.values()).filter(carType -> carType.value == value).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("invalid car size found: " + value));
    }

    @JsonValue
    public int toValue() {
        return value;
    }

}
