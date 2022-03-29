package com.park.spacemng.service.space.driver.imp;

import com.park.spacemng.config.LocationSelectionProperties;
import com.park.spacemng.model.constants.LocationSelectionType;
import com.park.spacemng.service.location.model.DesiredLocationRetrievalResult;
import com.park.spacemng.service.location.strategy.LocationOperationStrategy;
import com.park.spacemng.service.space.driver.DriverSpaceOperationService;
import com.park.spacemng.service.space.driver.mapper.DriverSpaceOperationMapper;
import com.park.spacemng.service.space.driver.model.DriverSpaceBookingModel;
import com.park.spacemng.service.space.driver.model.DriverSpaceBookingResult;
import com.park.spacemng.service.space.driver.model.NearbyAvailableSpacesRetrievalModel;
import com.park.spacemng.service.space.driver.model.NearbyAvailableSpacesRetrievalResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class DriverSpaceOperationServiceImpl implements DriverSpaceOperationService {

	private final LocationOperationStrategy locationStrategy;

	private final LocationSelectionProperties properties;

	private final DriverSpaceOperationMapper mapper;

	@Override

	public NearbyAvailableSpacesRetrievalResult getNearbyAvailableSpaces(NearbyAvailableSpacesRetrievalModel model) {
		DesiredLocationRetrievalResult desiredLocation = locationStrategy
				.getLocationOperationService(getLocationSelectionType())
				.getDesiredLocation(mapper.toDesiredLocationRetrievalModel(model));
	}

	@Override
	public DriverSpaceBookingResult bookSpace(DriverSpaceBookingModel model) {
		return null;
	}

	private LocationSelectionType getLocationSelectionType() {
		return properties.getDefaultType();
	}

}