package com.park.spacemng.service.space.driver.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NearbyAvailableSpacesRetrievalResult {

	private List<SpaceDetails> spaces;

}