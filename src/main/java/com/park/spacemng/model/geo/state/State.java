package com.park.spacemng.model.geo.state;

import java.util.Map;

import com.park.spacemng.model.constants.District;
import com.park.spacemng.model.constants.StateName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.springframework.data.geo.Polygon;

@Setter
@Getter
@ToString
public class State {

	private State uid;

	private String name;

	private StateName stateName;

	private Map<District, Polygon> districts;

}