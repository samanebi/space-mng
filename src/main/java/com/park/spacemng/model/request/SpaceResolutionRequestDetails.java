package com.park.spacemng.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.park.spacemng.model.constants.CarType;
import com.park.spacemng.model.constants.RequestResolution;
import lombok.Data;

@Data
public class SpaceResolutionRequestDetails {

	@NotNull
	@NotBlank
	private String batchId;

	@NotNull
	@NotBlank
	private String driverId;

	@NotNull
	@NotBlank
	private String trackingCode;

	@NotNull
	private RequestResolution resolution;

	@NotNull
	private CarType carSize;

}