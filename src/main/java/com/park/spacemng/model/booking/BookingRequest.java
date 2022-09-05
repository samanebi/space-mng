package com.park.spacemng.model.booking;

import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.park.spacemng.model.constants.CarSize;
import com.park.spacemng.model.mogo.MongoBaseEntity;
import com.park.spacemng.model.user.driver.Driver;
import com.park.spacemng.model.user.owner.Owner;
import lombok.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@ToString
public class BookingRequest extends MongoBaseEntity {

	private String trackingCode;

	private String batchId;

	private String spaceTitle;

	private String spaceId;

	private String carId;

	@CreatedDate
	private long creationDate;

	private long exerciseDate;

	private long stateChangedDate;

	private Owner owner;

	private Driver driver;

	private long price;

	private PaymentProviderResponse providerResponse;

	private Status status;

	private CarSize carSize;

	@AllArgsConstructor
	public enum Status {

		INITIATED(0),
		ACCEPTED(1),
		REJECTED(2),
		PAYED(3),
		CONFIRMED(4),
		EXPIRED(5);

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