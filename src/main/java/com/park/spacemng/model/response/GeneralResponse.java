package com.park.spacemng.model.response;

import com.park.spacemng.model.constants.ProcessStatus;
import lombok.Data;

@Data
public class GeneralResponse {

	private final ProcessStatus status;

	private final String message;

	public GeneralResponse(ProcessStatus status) {
		this.status = status;
		this.message = "failure";
	}

	public GeneralResponse(ProcessStatus status, String message) {
		this.status = status;
		this.message = message;
	}
}