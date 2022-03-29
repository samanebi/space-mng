package com.park.spacemng.model.geo.state;

import java.util.Map;

import com.park.spacemng.model.constants.District;
import com.park.spacemng.model.constants.StateName;
import lombok.Data;

import org.springframework.data.geo.Polygon;

@Data
public class State {

	private State uid;

	private String name;

	private StateName stateName;

	private Map<District, Polygon> districts;

}