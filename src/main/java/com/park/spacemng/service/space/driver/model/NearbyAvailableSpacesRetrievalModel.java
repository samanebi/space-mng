package com.park.spacemng.service.space.driver.model;

import java.awt.Point;

import lombok.Data;

@Data
public class NearbyAvailableSpacesRetrievalModel {

	private String driverId;

	private String carId;

	private Point location;

}