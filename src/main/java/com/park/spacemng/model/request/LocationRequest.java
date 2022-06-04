package com.park.spacemng.model.request;

import javax.validation.constraints.NotNull;

import com.park.spacemng.model.constants.District;
import com.park.spacemng.model.constants.StateName;
import com.park.spacemng.model.constants.Town;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.springframework.data.geo.Point;

@Setter
@Getter
@ToString
public class LocationRequest {

	@NotNull
	private Point location;

	@NotNull
	private StateName stateName;

	@NotNull
	private Town town;

	@NotNull
	private District district;

}