package com.park.spacemng.exception;

import com.park.spacemng.model.constants.ProcessStatus;

public class SpaceNotAvailableException extends GeneralException {

	public SpaceNotAvailableException(String message) {
		super(ProcessStatus.FAILURE, message);
	}

}