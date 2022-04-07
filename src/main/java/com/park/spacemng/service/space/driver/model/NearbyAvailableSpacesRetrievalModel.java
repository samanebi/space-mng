package com.park.spacemng.service.space.driver.model;

import java.awt.Point;

import com.park.spacemng.model.constants.StateName;
import com.park.spacemng.model.constants.Town;
import lombok.Data;

@Data
public class NearbyAvailableSpacesRetrievalModel {

	private String driverId;

	private String carId;

	private Point location;

	private Town town = Town.TEHRAN;

	private StateName stateName = StateName.TEHRAN;

}