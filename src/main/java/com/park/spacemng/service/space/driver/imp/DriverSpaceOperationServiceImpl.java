package com.park.spacemng.service.space.driver.imp;

import java.util.ArrayList;
import java.util.List;

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
import com.park.spacemng.service.space.driver.model.SpaceDetails;
import com.park.spacemng.service.space.owner.OnlineOwnerOperationService;
import com.park.spacemng.service.space.owner.OwnerSpaceOperationService;
import com.park.spacemng.service.space.owner.model.OnlineOwnerRetrievalModel;
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

	private final OwnerSpaceOperationService ownerSpaceOperationService;

	private final OnlineOwnerOperationService onlineOwnerOperationService;

	@Override
	public NearbyAvailableSpacesRetrievalResult getNearbyAvailableSpaces(NearbyAvailableSpacesRetrievalModel model) {
		DesiredLocationRetrievalResult desiredLocation = locationStrategy
				.getLocationOperationService(getLocationSelectionType())
				.getDesiredLocation(mapper.toDesiredLocationRetrievalModel(model));
		List<SpaceDetails> result = new ArrayList<>();
		ownerSpaceOperationService.querySpaces(mapper.toOwnerSpaceRetrievalModel(desiredLocation)).getSpaces()
				.forEach(space -> {
					if (onlineOwnerOperationService
							.isOnline(new OnlineOwnerRetrievalModel(space.getOwner().getOwnerId())).isOnline()) {
						result.add(mapper.toSpaceDetails(space));
					}
				});
		return new NearbyAvailableSpacesRetrievalResult(result);
	}

	@Override
	public DriverSpaceBookingResult bookSpace(DriverSpaceBookingModel model) {
		return null;
	}

	private LocationSelectionType getLocationSelectionType() {
		return properties.getDefaultType();
	}

}