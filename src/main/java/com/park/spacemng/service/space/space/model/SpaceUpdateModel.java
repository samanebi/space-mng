package com.park.spacemng.service.space.space.model;

import com.park.spacemng.model.space.Location;
import lombok.Data;

@Data
public class SpaceUpdateModel {

	private String batchId;

	private String ownerId;

	private String title;

	private String description;

	private String address;

	private Location location;

	private int capacity;

}