package com.park.spacemng.service.space.driver.model;

import lombok.Data;

@Data
public class DriverSpaceBookingResult {

	private Location location;

	private OwnerDetails owner;

	private String spaceId;

	private String batchId;

	private String title;

	private String address;

	private String description;

}