package com.park.spacemng.exception;

import com.park.spacemng.model.constants.ProcessStatus;

public class SpaceNotFoundException extends GeneralException {

	public SpaceNotFoundException(String message) {
		super(ProcessStatus.SPACE_NOT_FOUND, message);
	}

}