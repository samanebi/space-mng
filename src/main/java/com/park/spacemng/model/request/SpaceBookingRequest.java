package com.park.spacemng.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

import lombok.Data;

@Data
public class SpaceBookingRequest {

	@NotBlank
	private String batchId;

	@NotBlank
	private String driverId;

	@PositiveOrZero
	private long amount;

}