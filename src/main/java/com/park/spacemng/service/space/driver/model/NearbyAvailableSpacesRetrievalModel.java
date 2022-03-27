package com.park.spacemng.service.space.driver.model;

import lombok.Data;

@Data
public class NearbyAvailableSpacesRetrievalModel {

	private String driverId;

	private String carId;

	private Location location;

}