package com.park.spacemng.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class NearbyAvailableSpacesRequest {

	@NotBlank
	private String driverId;

	@NotBlank
	private String carId;

	@NotNull
	private LocationRequest location;

}