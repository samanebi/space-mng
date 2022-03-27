package com.park.spacemng.model.user;

import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.park.spacemng.model.user.constants.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class User {

	private String userId;

	private String name;

	private String surname;

	private String cellNumber;

	private String email;

	private long birthday;

	private Gender gender;

	private Status status;

	@AllArgsConstructor
	public enum Status {

		ACTIVE(0), BLOCKED(1);

		private final int value;

		@JsonCreator
		public static Status fromValue(int value) {
			return Stream.of(Status.values()).filter(status -> status.value == value).findFirst()
					.orElseThrow(() -> new IllegalArgumentException("invalid status found: " + value));
		}

		@JsonValue
		public int toValue() {
			return value;
		}

	}
}