package com.park.spacemng.service.booking.model;

import lombok.Data;

@Data
public class BookingInitiationModel {

	private String batchId;

	private String ownerId;

	private String driverId;

	private long amount;

}