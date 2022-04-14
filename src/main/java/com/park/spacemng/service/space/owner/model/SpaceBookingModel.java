package com.park.spacemng.service.space.owner.model;

import lombok.Data;

@Data
public class SpaceBookingModel {

	private String trackingCode;

	private String batchId;

	private String driverId;

	private String carId;

	private long amount;

}