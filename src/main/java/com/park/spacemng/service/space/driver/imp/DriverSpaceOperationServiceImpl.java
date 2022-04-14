package com.park.spacemng.service.space.driver.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

import com.park.spacemng.config.LocationSelectionProperties;
import com.park.spacemng.exception.DriverNotFoundException;
import com.park.spacemng.exception.GeneralException;
import com.park.spacemng.exception.ParameterValidationException;
import com.park.spacemng.exception.SpaceNotAvailableException;
import com.park.spacemng.model.constants.LocationSelectionType;
import com.park.spacemng.model.space.Space.Status;
import com.park.spacemng.service.booking.BookingOperationService;
import com.park.spacemng.service.booking.model.BookingInitiationResult;
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
import com.park.spacemng.service.space.space.SpaceOperationService;
import com.park.spacemng.service.space.space.model.SpaceInfo;
import com.park.spacemng.service.user.driver.DriverOperationService;
import com.park.spacemng.service.user.driver.model.DriverInfo;
import com.park.spacemng.service.user.driver.model.DriverRetrievalModel;
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

	private final DriverOperationService driverOperationService;

	private final SpaceOperationService spaceOperationService;

	private final BookingOperationService bookingOperationService;

	@Override
	public NearbyAvailableSpacesRetrievalResult getNearbyAvailableSpaces(NearbyAvailableSpacesRetrievalModel model) throws ParameterValidationException {
		nearbyAvailableSpacesArgumentValidation(model);

		log.info("going to get nearby available spaces for request : {}", model);
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

	private void nearbyAvailableSpacesArgumentValidation(NearbyAvailableSpacesRetrievalModel model) throws ParameterValidationException {
		if (Objects.isNull(model.getDriverId())) {
			throw new ParameterValidationException("driver id is null in request : " + model);
		}
		if (Objects.isNull(model.getCarId())) {
			throw new ParameterValidationException("car id is null in request : " + model);
		}
		if (Objects.isNull(model.getLocation())) {
			throw new ParameterValidationException("location is null in request : " + model);
		}
		if (Objects.equals(model.getLocation().getY(), 0.0)) {
			throw new ParameterValidationException("location x is zero in request : " + model);
		}
		if (Objects.equals(model.getLocation().getX(), 0.0)) {
			throw new ParameterValidationException("location y is zero in request : " + model);
		}
	}

	@Override
	public DriverSpaceBookingResult bookSpace(DriverSpaceBookingModel model) throws GeneralException {
		bookSpaceArgumentValidation(model);

		log.info("going to book space for request : {}", model);
		Optional<DriverInfo> driverInfo = driverOperationService
				.retriveDriver(new DriverRetrievalModel(model.getDriverId()));
		List<SpaceInfo> spaceInfo = spaceOperationService
				.retrieveSpace(model.getBatchId(), Status.FREE);
		if (spaceInfo.isEmpty()) {
			log.error("no free space available with batch id : {}", model.getBatchId());
			throw new SpaceNotAvailableException("no free space available with batch id : " + model.getBatchId());
		}
		if (driverInfo.isEmpty()) {
			log.error("driver not found with driver id : {}", model.getDriverId());
			throw new DriverNotFoundException("driver not found with driver id : " + model.getDriverId());
		}
		int index = new Random().nextInt(spaceInfo.size());
		SpaceInfo info = spaceInfo.get(index);
		spaceOperationService.takeUnderProcess(info.getSpaceId(), info.getBatchId());
		BookingInitiationResult bookingInitiationResult = bookingOperationService
				.initiateBookingRequest(mapper.toBookingInitiationModel(model,
						info.getOwner().getOwnerId()));
		return mapper.toDriverSpaceBookingResult(info, bookingInitiationResult.getTrackingCode());
	}

	private void bookSpaceArgumentValidation(DriverSpaceBookingModel model) throws ParameterValidationException {
		if (Objects.isNull(model.getDriverId())) {
			log.error("driver id is null in request : {}", model);
			throw new ParameterValidationException("driver id is null in request : " + model);
		}
		if (Objects.isNull(model.getBatchId())) {
			log.error("batch id is null in request : {}", model);
			throw new ParameterValidationException("batch id is null in request : " + model);
		}
		if (Objects.isNull(model.getCarId())) {
			log.error("car id is null in request : {}", model);
			throw new ParameterValidationException("car id is null in request : " + model);
		}
		if (Objects.isNull(model.getUserId())) {
			log.error("user id is null in request : {}", model);
			throw new ParameterValidationException("user id is null in request : " + model);
		}
		if (Objects.equals(model.getAmount(), 0L)) {
			log.error("amount is zero in request : {}", model);
			throw new ParameterValidationException("amount is zero in request : " + model);
		}
	}

	private LocationSelectionType getLocationSelectionType() {
		return properties.getDefaultType();
	}

}