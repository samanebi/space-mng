package com.park.spacemng.exception;

import com.park.spacemng.model.constants.ProcessStatus;

public class ParameterValidationException extends GeneralException {

	private static final long serialVersionUID = -3749766539158141006L;

	public ParameterValidationException(String message) {
		super(ProcessStatus.FAILURE, message);
	}

}