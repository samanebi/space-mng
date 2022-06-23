package com.park.spacemng.service.space.space.model;

import java.util.List;

import com.park.spacemng.model.constants.District;
import com.park.spacemng.model.constants.StateName;
import com.park.spacemng.model.constants.Town;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class SpaceQueryModel {

	private List<Town> towns;

	private List<District> districts;

	private List<StateName> states;

}