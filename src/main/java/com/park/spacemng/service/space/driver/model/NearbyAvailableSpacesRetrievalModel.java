package com.park.spacemng.service.space.driver.model;

import com.park.spacemng.model.constants.StateName;
import com.park.spacemng.model.constants.Town;
import lombok.Data;

import org.springframework.data.geo.Point;

@Data
public class NearbyAvailableSpacesRetrievalModel {

	private String driverId;

	private String carId;

	private Point location;

	private Town town = Town.TEHRAN;

	private StateName stateName = StateName.TEHRAN;

}