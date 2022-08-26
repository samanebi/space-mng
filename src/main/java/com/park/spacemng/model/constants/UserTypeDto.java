package com.park.spacemng.model.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum UserTypeDto {

    DRIVER(0), OWNER(1);
    private final int value;

    UserTypeDto(int value) {
        this.value = value;
    }

    @JsonCreator
    public static UserTypeDto fromValue(int value) {
        return Stream.of(UserTypeDto.values()).filter(state -> state.value == value).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("invalid geo state found: " + value));
    }

    @JsonValue
    public int toValue() {
        return value;
    }

}
