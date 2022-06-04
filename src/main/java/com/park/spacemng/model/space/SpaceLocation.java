package com.park.spacemng.model.space;

import com.park.spacemng.model.constants.District;
import com.park.spacemng.model.constants.StateName;
import com.park.spacemng.model.constants.Town;
import lombok.Getter;
import lombok.Setter;

import org.springframework.data.geo.Point;

@Setter
@Getter
public class SpaceLocation {

	private Point position;

	private StateName stateName;

	private Town town;

	private District district;

}