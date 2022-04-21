package com.park.spacemng.exception;

import com.park.spacemng.model.constants.ProcessStatus;

public class OwnerNotFoundException extends GeneralException {

	public OwnerNotFoundException(String message) {
		super(ProcessStatus.FAILURE, message);
	}

}