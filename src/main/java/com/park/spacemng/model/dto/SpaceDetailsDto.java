package com.park.spacemng.model.dto;

import lombok.Data;

@Data
public class SpaceDetailsDto {

	private LocationDto location;

	private OwnerDto owner;

	private String batchId;

	private String title;

	private String address;

	private String description;

	private long price;

	private long entryFee;

}