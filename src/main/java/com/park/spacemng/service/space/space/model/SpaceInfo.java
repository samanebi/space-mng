package com.park.spacemng.service.space.space.model;

import com.park.spacemng.model.space.Location;
import com.park.spacemng.model.space.Space;
import com.park.spacemng.service.user.owner.model.OwnerInfo;
import lombok.Data;

@Data
public class SpaceInfo {

	private String spaceId;

	private String batchId;

	private String title;

	private String description;

	private String address;

	private Location location;

	private OwnerInfo owner;

	private Space.Status status;

}