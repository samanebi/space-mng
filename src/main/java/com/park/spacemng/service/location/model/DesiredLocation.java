package com.park.spacemng.service.location.model;

import java.util.List;

import com.park.spacemng.model.constants.District;
import com.park.spacemng.model.constants.StateName;
import com.park.spacemng.model.constants.Town;
import lombok.Data;

@Data
public class DesiredLocation {

	private List<District> districts;

	private List<Town> town;

	private List<StateName> stateName;

}