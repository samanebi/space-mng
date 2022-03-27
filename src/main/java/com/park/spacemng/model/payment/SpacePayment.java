package com.park.spacemng.model.payment;

import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.park.spacemng.model.payment.constants.PaymentGateway;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class SpacePayment {

	private String ticket;

	private String trackingCode;

	private long amount;

	private String batchId;

	private long creationDate;

	private long exerciseDate;

	private PaymentGateway gateway;

	private Status status;

	private ProviderResponse providerResponse;

	@AllArgsConstructor
	public enum Status {

		INITIATED(0), PAYED(1), CONFIRMED(2);

		private final int value;

		@JsonCreator
		public static Status fromValue(int value) {
			return Stream.of(Status.values()).filter(status -> status.value == value).findFirst()
					.orElseThrow(() -> new IllegalArgumentException("invalid payment status found: " + value));
		}

		@JsonValue
		public int toValue() {
			return value;
		}

	}

}