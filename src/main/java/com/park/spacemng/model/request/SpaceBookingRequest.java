package com.park.spacemng.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.park.spacemng.model.constants.RequestResolution;
import lombok.Data;

@Data
public class SpaceBookingRequest {

	@NotBlank
	private String batchId;

	@NotBlank
	private String driverId;

	@NotBlank
	private String carId;

	@PositiveOrZero
	private long amount;

	@NotNull
	private RequestResolution resolution;

}