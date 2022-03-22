package com.park.spacemng.model.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class SpaceBookingRequest {

	@NotBlank
	private String batchId;

	@NotBlank
	private String driverId;

	@NotBlank
	private String carId;

	@NotBlank
	private String paymentTrackingCode;

}