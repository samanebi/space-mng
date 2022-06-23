package com.park.spacemng.model.space;

import java.io.Serial;
import java.io.Serializable;

import com.park.spacemng.model.constants.District;
import com.park.spacemng.model.constants.StateName;
import com.park.spacemng.model.constants.Town;
import lombok.Getter;
import lombok.Setter;

import org.springframework.data.geo.Point;

@Setter
@Getter
public class SpaceLocation implements Serializable {

	@Serial
	private static final long serialVersionUID = -8152519738572814358L;

	private Point position;

	private StateName stateName;

	private Town town;

	private District district;

}