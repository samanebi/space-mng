package com.park.spacemng.exception;

import com.park.spacemng.model.constants.ProcessStatus;

public class OwnerNotFoundException extends GeneralException {

	public OwnerNotFoundException(String message) {
		super(ProcessStatus.OWNER_NOT_FOUND, message);
	}

}