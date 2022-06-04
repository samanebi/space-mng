package com.park.spacemng.service.location.strategy;

import java.util.HashMap;
import java.util.Map;

import com.park.spacemng.model.constants.LocationSelectionType;
import com.park.spacemng.service.location.LocationOperationService;
import com.park.spacemng.service.location.impl.DistrictBasedLocationOperationService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocationOperationStrategyImpl implements LocationOperationStrategy {

	private final Map<LocationSelectionType, LocationOperationService> services = new HashMap<>();

	private final DistrictBasedLocationOperationService defaultService;

	@Override
	public LocationOperationService getLocationOperationService(LocationSelectionType type) {
		return services.getOrDefault(type, defaultService);
	}

}