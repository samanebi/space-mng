package com.park.spacemng.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

import lombok.Data;

@Data
public class SpaceAllocationRequest {

	@NotBlank
	private String batchId;

	@NotBlank
	private String driverId;

	@NotBlank
	private String carId;

	@PositiveOrZero
	private long amount;

}