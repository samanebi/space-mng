package com.park.spacemng.exception;

import com.park.spacemng.model.constants.ProcessStatus;

public class StateNotFoundException extends GeneralException {

	public StateNotFoundException(String message) {
		super(ProcessStatus.FAILURE, message);
	}

}