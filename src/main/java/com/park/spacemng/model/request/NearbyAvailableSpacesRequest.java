package com.park.spacemng.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

import org.springframework.data.geo.Point;

@Data
public class NearbyAvailableSpacesRequest {

	@NotBlank
	private String driverId;

	@NotBlank
	private String carId;

	@NotNull
	private Point location;

}