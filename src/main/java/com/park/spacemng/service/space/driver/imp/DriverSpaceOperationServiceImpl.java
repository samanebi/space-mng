package com.park.spacemng.service.space.driver.imp;

import com.park.spacemng.exception.GeneralException;
import com.park.spacemng.exception.SpaceNotAvailableException;
import com.park.spacemng.model.booking.BookingRequest;
import com.park.spacemng.model.space.space.Space.Status;
import com.park.spacemng.model.user.driver.Driver;
import com.park.spacemng.service.booking.BookingOperationService;
import com.park.spacemng.service.space.driver.DriverSpaceOperationService;
import com.park.spacemng.service.space.driver.mapper.DriverSpaceOperationMapper;
import com.park.spacemng.service.space.driver.model.*;
import com.park.spacemng.service.space.owner.OnlineOwnerOperationService;
import com.park.spacemng.service.space.owner.OwnerSpaceOperationService;
import com.park.spacemng.service.space.space.SpaceOperationService;
import com.park.spacemng.service.space.space.model.SpaceInfo;
import com.park.spacemng.service.user.user.UserOperationService;
import com.park.spacemng.util.ParameterValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
public class DriverSpaceOperationServiceImpl implements DriverSpaceOperationService {

	private static final Random RANDOM = new Random();

	private final DriverSpaceOperationMapper mapper;

	private final OwnerSpaceOperationService ownerSpaceOperationService;

	private final OnlineOwnerOperationService onlineOwnerOperationService;

	private final UserOperationService<Driver> driverOperationService;

	private final SpaceOperationService spaceOperationService;

	private final BookingOperationService bookingOperationService;

	private final ParameterValidator parameterValidator;

	@Override
	public NearbyAvailableSpacesRetrievalResult getNearbyAvailableSpaces(NearbyAvailableSpacesRetrievalModel model) throws GeneralException {
		nearbyAvailableSpacesArgumentValidation(model);

		log.info("going to get nearby available spaces for request : {}", model);
		/*DesiredLocationRetrievalResult desiredLocation = locationStrategy
				.getLocationOperationService(getLocationSelectionType())
				.getDesiredLocation(mapper.toDesiredLocationRetrievalModel(model));*/
		List<SpaceDetails> result = new ArrayList<>();
		ownerSpaceOperationService.findSpaces(model.getLocation()).getSpaces()
				.forEach(space -> {
					if (onlineOwnerOperationService
							.isOnline(space.getOwner().getId())) {
						result.add(mapper.toSpaceDetails(space));
					}
				});
		return new NearbyAvailableSpacesRetrievalResult(result);
	}

	private void nearbyAvailableSpacesArgumentValidation(NearbyAvailableSpacesRetrievalModel model) throws GeneralException {
		parameterValidator.requireParameterNotNullOrBlank(model.getDriverId());
		parameterValidator.requireParameterNotNullOrBlank(model.getCarId());
		parameterValidator.requireParameterNotNull(model.getLocation());
		parameterValidator.requireParameterNotEqualTo(model.getLocation().getY(), 0.0);
		parameterValidator.requireParameterNotEqualTo(model.getLocation().getX(), 0.0);
	}

	@Override
	public DriverSpaceBookingResult bookSpace(DriverSpaceBookingModel model) throws GeneralException {
		bookSpaceArgumentValidation(model);

		log.info("going to book space for request : {}", model);
		driverOperationService.retrieveUser(model.getDriverId());
		List<SpaceInfo> spaceInfos = spaceOperationService
				.retrieveSpace(model.getBatchId(), Status.FREE);
		if (spaceInfos.isEmpty()) {
			log.error("no free space available with batch id : {}", model.getBatchId());
			throw new SpaceNotAvailableException("no free space available with batch id : " + model.getBatchId());
		}

		int index = RANDOM.nextInt(spaceInfos.size());
		SpaceInfo info = spaceInfos.get(index);
		spaceOperationService.takeUnderProcess(info.getSpaceId());
		String trackingCode = bookingOperationService
				.initiateBookingRequest(mapper.toBookingInitiationModel(model, info));
		return mapper.toDriverSpaceBookingResult(info, trackingCode);
	}

	@Override
	public void evacuate(String trackingCode) {
		bookingOperationService.evacuate(trackingCode);
	}

	@Override
	public BookingRequest getRequest(String trackingCode) {
		return bookingOperationService.getRequest(trackingCode);
	}

	private void bookSpaceArgumentValidation(DriverSpaceBookingModel model) throws GeneralException {
		parameterValidator.requireParameterNotNullOrBlank(model.getDriverId());
		parameterValidator.requireParameterNotNullOrBlank(model.getBatchId());
	}

}