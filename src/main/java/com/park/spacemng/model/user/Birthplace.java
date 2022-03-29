package com.park.spacemng.model.user;

import com.park.spacemng.model.constants.StateName;
import lombok.Data;

@Data
public class Birthplace {

	private StateName birthStateName;

	private StateName birthTown;

}