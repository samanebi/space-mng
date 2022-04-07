package com.park.spacemng.service.space.space.model;

import com.park.spacemng.service.space.owner.model.Location;
import lombok.Data;

@Data
public class SpaceGenerationModel {

	private String batchId;

	private String title;

	private String description;

	private String address;

	private Location location;

	private int capacity;

}