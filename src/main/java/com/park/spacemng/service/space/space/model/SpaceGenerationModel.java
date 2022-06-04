package com.park.spacemng.service.space.space.model;

import com.park.spacemng.model.space.SpaceLocation;
import lombok.Data;

@Data
public class SpaceGenerationModel {

	private String batchId;

	private String ownerId;

	private String title;

	private String description;

	private String address;

	private SpaceLocation spaceLocation;

	private int capacity;

}