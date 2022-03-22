package com.park.spacemng.model.user;

import com.park.spacemng.model.constants.State;
import lombok.Data;

@Data
public class Birthplace {

	private State birthState;

	private State birthTown;

}