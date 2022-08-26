package com.park.spacemng.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class SpaceUpdateRequest {

	private String batchId;

	private String title;

	private String description;

	private String address;

	private LocationRequest location;

	private int capacity;

	private long price;

}