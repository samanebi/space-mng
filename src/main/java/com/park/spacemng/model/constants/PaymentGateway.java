package com.park.spacemng.model.constants;

import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PaymentGateway {

	IPG(0), DPG(1);

	private final int value;

	@JsonCreator
	public static PaymentGateway fromValue(int value) {
		return Stream.of(PaymentGateway.values()).filter(gateway -> gateway.value == value).findFirst()
				.orElseThrow(() -> new IllegalArgumentException("invalid payment gateway found: " + value));
	}

	@JsonValue
	public int toValue() {
		return value;
	}

}