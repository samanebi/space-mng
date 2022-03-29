package com.park.spacemng.service.space.driver.model;

import java.awt.Point;

import com.park.spacemng.model.constants.District;
import com.park.spacemng.model.constants.StateName;
import com.park.spacemng.model.constants.Town;
import lombok.Data;

@Data
public class DriverLocation {

	private Point location;

	private StateName stateName;

	private Town town;

	private District district;

}