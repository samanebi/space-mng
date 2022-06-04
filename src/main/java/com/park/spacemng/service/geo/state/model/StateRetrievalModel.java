package com.park.spacemng.service.geo.state.model;

import com.park.spacemng.model.constants.StateName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class StateRetrievalModel {

	private StateName stateName;

}