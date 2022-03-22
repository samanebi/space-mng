package com.park.spacemng.model.dto;

public record SpaceDetailsDto(LocationDto location, OwnerDto owner
		, String spaceId, String batchId, String title, String address, String description) {
}