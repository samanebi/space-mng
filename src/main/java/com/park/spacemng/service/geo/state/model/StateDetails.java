package com.park.spacemng.service.geo.state.model;

import java.util.Map;

import com.park.spacemng.model.constants.District;
import com.park.spacemng.model.constants.StateName;
import lombok.Data;

import org.springframework.data.geo.Polygon;

@Data
public class StateDetails {

	private Map<District, Polygon> districts;

	private StateName state;

}