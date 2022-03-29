package com.park.spacemng.service.location.model;

import com.park.spacemng.model.constants.StateName;
import com.park.spacemng.model.constants.Town;
import lombok.Data;

import org.springframework.data.geo.Point;

@Data
public class DesiredLocationRetrievalModel {

	private Point location;

	private Town town;

	private StateName stateName;

}