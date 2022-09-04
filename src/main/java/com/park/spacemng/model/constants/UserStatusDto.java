package com.park.spacemng.model.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum UserStatusDto {

    OFFLINE(0), ONLINE(1);

    private final int value;

    UserStatusDto(int value) {
        this.value = value;
    }

    @JsonCreator
    public static UserStatusDto fromValue(int value) {
        return Stream.of(UserStatusDto.values()).filter(state -> state.value == value).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("invalid user type found: " + value));
    }

    @JsonValue
    public int toValue() {
        return value;
    }

}
