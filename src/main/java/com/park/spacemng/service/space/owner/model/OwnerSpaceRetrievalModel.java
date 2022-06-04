package com.park.spacemng.service.space.owner.model;

import java.util.List;

import com.park.spacemng.model.constants.District;
import com.park.spacemng.model.constants.StateName;
import com.park.spacemng.model.constants.Town;
import lombok.Data;

@Data
public class OwnerSpaceRetrievalModel {

	private List<Town> towns;

	private List<District> districts;

	private List<StateName> states;

}