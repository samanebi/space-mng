package com.park.spacemng.service.space.driver.model;

import java.util.List;

import lombok.Data;

@Data
public class NearbyAvailableSpacesRetrievalResult {

	private List<SpaceDetails> spaces;

}