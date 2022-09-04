package com.park.spacemng.exception;

import com.park.spacemng.model.constants.ProcessStatus;

public class SpaceNotAvailableException extends GeneralException {

	public SpaceNotAvailableException(String message) {
		super(ProcessStatus.SPACE_NOT_AVAILABLE, message);
	}

}