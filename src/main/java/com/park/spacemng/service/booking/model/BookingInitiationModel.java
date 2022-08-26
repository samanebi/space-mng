package com.park.spacemng.service.booking.model;

import lombok.Data;

@Data
public class BookingInitiationModel {

	private String batchId;

	private String spaceId;

	private String carId;

	private String ownerId;

	private String driverId;

	private long price;

}