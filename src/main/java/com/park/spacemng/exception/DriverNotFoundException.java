package com.park.spacemng.exception;

import com.park.spacemng.model.constants.ProcessStatus;

public class DriverNotFoundException extends GeneralException {

	public DriverNotFoundException(String message) {
		super(ProcessStatus.DRIVER_NOT_FOUND, message);
	}

}