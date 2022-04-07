package com.park.spacemng.service.space.owner.model;

import lombok.Data;

@Data
public class OwnerSpaceUpdateModel {

	private String batchId;

	private String title;

	private String description;

	private String address;

	private Location location;

	private int capacity;

}