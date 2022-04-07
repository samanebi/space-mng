package com.park.spacemng.model.response;

import com.park.spacemng.model.constants.ProcessStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GeneralResponse {

	private ProcessStatus status;

	private String message;

	public GeneralResponse(ProcessStatus status) {
		if (status == ProcessStatus.SUCCESS) {
			this.message = "success.";
		} else {
			this.message = "failure.";
		}
		this.status = status;
	}

	public GeneralResponse(ProcessStatus status, String message) {
		this.status = status;
		this.message = message;
	}

}