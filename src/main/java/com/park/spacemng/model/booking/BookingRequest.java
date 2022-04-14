package com.park.spacemng.model.booking;

import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.park.spacemng.model.user.Driver;
import com.park.spacemng.model.user.Owner;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class BookingRequest {

	private String trackingCode;

	private String batchId;

	private long creationDate;

	private long exerciseDate;

	private long stateChangedDate;

	private Owner owner;

	private Driver driver;

	private long amount;

	private PaymentProviderResponse providerResponse;

	private Status status;

	@AllArgsConstructor
	public enum Status {

		INITIATED(0),
		ACCEPTED(1),
		REJECTED(2),
		PAYED(3),
		CONFIRMED(4);

		private final int value;

		@JsonCreator
		public static Status fromValue(int value) {
			return Stream.of(Status.values()).filter(status -> status.value == value).findFirst()
					.orElseThrow(() -> new IllegalArgumentException("invalid space statue found: " + value));
		}

		@JsonValue
		public int toValue() {
			return value;
		}

	}

}