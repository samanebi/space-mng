package com.park.spacemng.service.space.driver.model;

import lombok.Data;

@Data
public class DriverSpaceBookingModel {

	private String batchId;

	private String driverId;

	private String carId;

	private long amount;

}