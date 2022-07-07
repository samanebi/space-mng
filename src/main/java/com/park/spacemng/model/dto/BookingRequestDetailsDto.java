package com.park.spacemng.model.dto;

import com.park.spacemng.model.booking.BookingRequest.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class BookingRequestDetailsDto {

	private String trackingCode;

	private String batchId;

	private String spaceId;

	private String carId;

	private long creationDate;

	private long exerciseDate;

	private long stateChangedDate;

	private Status status;
	
}