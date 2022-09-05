package com.park.spacemng.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.geo.Point;

@Setter
@Getter
@ToString
public class NearbyAvailableSpacesRequest {

	@NotBlank
	private String driverId;

	@NotBlank
	private String carId;

	@NotNull
	private Point location;

}