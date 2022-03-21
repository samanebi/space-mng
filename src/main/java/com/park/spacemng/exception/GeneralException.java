package com.park.spacemng.exception;

import com.park.spacemng.model.constants.ProcessStatus;

public class GeneralException extends Exception {

	private static final long serialVersionUID = -3749766539158141005L;

	private final ProcessStatus status;

	public GeneralException(ProcessStatus status, String message) {
		super(message);
		this.status = status;
	}
}