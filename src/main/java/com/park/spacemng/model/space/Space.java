package com.park.spacemng.model.space;

import com.park.spacemng.model.user.Owner;
import lombok.Data;

@Data
public class Space {

	private final String spaceId;

	private final String batchId;

	private String title;

	private String description;

	private String address;

	private Location location;

	private Owner owner;

}