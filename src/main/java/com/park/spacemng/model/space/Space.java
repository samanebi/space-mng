package com.park.spacemng.model.space;

import com.park.spacemng.model.user.Owner;
import lombok.Data;

@Data
public class Space {

	private final String businessId;

	private String name;

	private String description;

	private int capacity;

	private Location location;

	private Owner owner;

}