package com.park.spacemng.service.space.owner.model;

import java.util.List;

import com.park.spacemng.model.constants.District;
import com.park.spacemng.model.constants.Town;
import com.park.spacemng.model.geo.state.State;
import lombok.Data;

@Data
public class OwnerSpaceRetrievalModel {

	private List<Town> towns;

	private List<District> districts;

	private List<State> states;

}