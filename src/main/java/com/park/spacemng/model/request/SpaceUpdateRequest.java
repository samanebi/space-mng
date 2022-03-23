package com.park.spacemng.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Data;

@Data
public class SpaceUpdateRequest {

	@NotBlank
	private String batchId;

	@NotBlank
	private String title;

	@NotBlank
	private String description;

	@NotBlank
	private String address;

	@NotNull
	private LocationRequest location;

	@Positive
	private int capacity;

}