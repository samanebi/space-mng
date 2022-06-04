package com.park.spacemng.service.space.driver.model;

import com.park.spacemng.model.constants.District;
import com.park.spacemng.model.constants.StateName;
import com.park.spacemng.model.constants.Town;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.springframework.data.geo.Point;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class DriverLocation {

	private Point position;

	private StateName stateName;

	private Town town;

	private District district;

}