package com.park.spacemng.service.space.driver.imp;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.park.spacemng.config.LocationSelectionProperties;
import com.park.spacemng.config.ParameterValidationMessageProperties;
import com.park.spacemng.config.TrackingCodeProperties;
import com.park.spacemng.exception.GeneralException;
import com.park.spacemng.model.booking.dao.BookingRequestDao;
import com.park.spacemng.model.constants.District;
import com.park.spacemng.model.constants.LocationSelectionType;
import com.park.spacemng.model.constants.StateName;
import com.park.spacemng.model.constants.Town;
import com.park.spacemng.model.geo.state.dao.StateDao;
import com.park.spacemng.model.space.SpaceLocation;
import com.park.spacemng.model.space.space.Space;
import com.park.spacemng.model.space.space.Space.Status;
import com.park.spacemng.model.space.space.dao.SpaceDao;
import com.park.spacemng.model.user.User;
import com.park.spacemng.model.user.driver.dao.DriverDao;
import com.park.spacemng.model.user.owner.Owner;
import com.park.spacemng.model.user.owner.dao.OwnerDao;
import com.park.spacemng.service.booking.BookingOperationService;
import com.park.spacemng.service.booking.mapper.BookingOperationServiceMapper;
import com.park.spacemng.service.booking.model.BookingInitiationModel;
import com.park.spacemng.service.geo.state.StateGeoOperationService;
import com.park.spacemng.service.geo.state.mapper.StateGeoOperationServiceMapper;
import com.park.spacemng.service.location.LocationOperationService;
import com.park.spacemng.service.location.impl.DistrictBasedLocationOperationService;
import com.park.spacemng.service.location.model.DesiredLocation;
import com.park.spacemng.service.location.model.DesiredLocationRetrievalModel;
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
import com.park.spacemng.service.space.owner.model.OwnerSpaceRetrievalModel;
import com.park.spacemng.service.space.owner.model.OwnerSpaceRetrievalResult;
import com.park.spacemng.service.space.space.SpaceOperationService;
import com.park.spacemng.service.space.space.mapper.SpaceOperationServiceMapper;
import com.park.spacemng.service.space.space.model.SpaceInfo;
import com.park.spacemng.service.trackincode.TrackingCodeOperationService;
import com.park.spacemng.service.user.driver.DriverOperationService;
import com.park.spacemng.service.user.driver.mapper.DriverOperationServiceMapper;
import com.park.spacemng.service.user.driver.model.DriverInfo;
import com.park.spacemng.service.user.owner.OwnerOperationService;
import com.park.spacemng.service.user.owner.mapper.OwnerOperationServiceMapper;
import com.park.spacemng.service.user.owner.model.OwnerInfo;
import com.park.spacemng.util.ParameterValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.geo.Point;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith({ SpringExtension.class })
@TestPropertySource("classpath:application-test.properties")
@EnableConfigurationProperties({ ParameterValidationMessageProperties.class, LocationSelectionProperties.class })
class DriverSpaceOperationServiceImplTest {

	@Autowired
	DriverSpaceOperationService service;

	@Autowired
	DriverSpaceOperationMapper mapper;

	@Autowired
	ParameterValidator parameterValidator;

	@MockBean
	LocationOperationStrategy locationStrategy;

	@MockBean
	OnlineOwnerOperationService onlineOwnerOperationService;

	@MockBean
	OwnerSpaceOperationService ownerSpaceOperationService;

	@MockBean
	LocationOperationService locationOperationService;

	@MockBean
	DriverOperationService driverOperationService;

	@MockBean
	SpaceOperationService spaceOperationService;

	@MockBean
	BookingOperationService bookingOperationService;

	@MockBean
	StateDao stateDao;

	@MockBean
	DriverDao driverDao;

	@MockBean
	SpaceDao spaceDao;

	@MockBean
	OwnerDao ownerDao;

	@MockBean
	BookingRequestDao bookingRequestDao;

	@Test
	void getNearbyAvailableSpaces_success() throws GeneralException {
		Point position = new Point(1.0, 1.0);
		Town town = Town.TEHRAN;
		StateName stateName = StateName.TEHRAN;
		List<District> districts = Arrays.asList(District.ONE, District.TWO);
		List<Town> towns = Collections.singletonList(Town.TEHRAN);
		List<StateName> states = Collections.singletonList(StateName.TEHRAN);
		LocationSelectionType locationSelectionType = LocationSelectionType.DISTRICT_BASED;
		String spaceId = "sample-space-id";
		String ownerId = "sample-owner-id";
		String batchId = "sample-batch-id";
		String spaceTitle = "sample-title";
		String spaceAddress = "sample-address";
		String spaceDescription = "sample-description";
		String ownerName = "sample-name";
		String ownerCellNumber = "sample-cell-number";
		User.Status ownerStatus = User.Status.ACTIVE;

		DesiredLocationRetrievalModel desiredLocationRetrievalModel = new DesiredLocationRetrievalModel();
		desiredLocationRetrievalModel.setStateName(stateName);
		desiredLocationRetrievalModel.setTown(town);
		desiredLocationRetrievalModel.setLocation(position);

		DesiredLocationRetrievalResult desiredLocationRetrievalResult = new DesiredLocationRetrievalResult();
		DesiredLocation desiredLocation = new DesiredLocation();
		desiredLocation.setDistricts(districts);
		desiredLocation.setTowns(towns);
		desiredLocation.setStates(states);
		desiredLocationRetrievalResult.setLocation(desiredLocation);

		OwnerSpaceRetrievalModel ownerSpaceRetrievalModel = new OwnerSpaceRetrievalModel();
		ownerSpaceRetrievalModel.setDistricts(districts);
		ownerSpaceRetrievalModel.setTowns(towns);
		ownerSpaceRetrievalModel.setStates(states);

		OwnerSpaceRetrievalResult ownerSpaceRetrievalResult = new OwnerSpaceRetrievalResult();
		Space firstSpace = new Space();
		firstSpace.setTitle(spaceTitle);
		firstSpace.setBatchId(batchId);
		firstSpace.setId(spaceId);
		firstSpace.setDescription(spaceDescription);
		firstSpace.setAddress(spaceAddress);
		firstSpace.setStatus(Status.FREE);
		Owner ownerOne = new Owner();
		ownerOne.setStatus(ownerStatus);
		ownerOne.setCellNumber(ownerCellNumber);
		ownerOne.setName(ownerName);
		ownerOne.setId(ownerId);
		firstSpace.setOwner(ownerOne);
		SpaceLocation spaceLocation = new SpaceLocation();
		spaceLocation.setStateName(stateName);
		spaceLocation.setTown(town);
		spaceLocation.setPosition(position);
		spaceLocation.setDistrict(districts.get(0));
		firstSpace.setLocation(spaceLocation);
		ownerSpaceRetrievalResult.setSpaces(Collections.singletonList(firstSpace));

		NearbyAvailableSpacesRetrievalModel model = new NearbyAvailableSpacesRetrievalModel();
		model.setDriverId("sample-driver-id");
		model.setLocation(new Point(1.0, 1.0));
		model.setCarId("sample-car-id");

		when(locationStrategy.getLocationOperationService(locationSelectionType))
				.thenReturn(locationOperationService);
		when(locationOperationService.getDesiredLocation(desiredLocationRetrievalModel))
				.thenReturn(desiredLocationRetrievalResult);
		when(ownerSpaceOperationService.querySpaces(any()))
				.thenReturn(ownerSpaceRetrievalResult);
		when(onlineOwnerOperationService.isOnline(ownerId)).thenReturn(true);

		NearbyAvailableSpacesRetrievalResult nearbyAvailableSpaces = service.getNearbyAvailableSpaces(model);

		assertThat(nearbyAvailableSpaces).isNotNull();
		assertThat(nearbyAvailableSpaces.getSpaces()).isNotEmpty();
		SpaceDetails spaceDetails = nearbyAvailableSpaces.getSpaces().get(0);
		assertThat(spaceDetails.getSpaceId()).isEqualTo(spaceId);
		assertThat(spaceDetails.getBatchId()).isEqualTo(batchId);
		assertThat(spaceDetails.getDescription()).isEqualTo(spaceDescription);
		assertThat(spaceDetails.getOwner().getOwnerId()).isEqualTo(ownerId);
		assertThat(spaceDetails.getOwner().getName()).isEqualTo(ownerName);
		assertThat(spaceDetails.getOwner().getStatus()).isEqualTo(ownerStatus);
	}

	@Test
	void bookSpace_success() throws GeneralException {
		String driverId = "sample-driver-id";
		String carId = "sample-car-id";
		String batchId = "sample-batch-id";
		String userId = "sample-user-id";
		String driverName = "driver-name";
		String driverSurname = "driver-surname";
		User.Status userStatus = User.Status.ACTIVE;
		long amount = 1000L;
		String spaceId = "sample-space-id";
		String spaceAddress = "sample-space-address";
		Status spaceStatus = Status.FREE;
		StateName spaceStateName = StateName.TEHRAN;
		Point spacePosition = new Point(10.0, 10.0);
		Town spaceTown = Town.TEHRAN;
		District spaceDistrict = District.ONE;
		String spaceTitle = "sample-space-title";
		String spaceDescription = "sample-space-description";
		String spaceOwnerId = "sample-owner-id";
		String spaceOwnerName = "sample-owner-name";
		String spaceOwnerSurname = "sample-owner-surname";
		String bookingTrackingCode = "sample-booking-tracking-code";

		DriverSpaceBookingModel model = new DriverSpaceBookingModel();
		model.setDriverId(driverId);
		model.setCarId(carId);
		model.setBatchId(batchId);
		model.setAmount(amount);

		DriverInfo driverInfo = new DriverInfo();
		driverInfo.setDriverId(driverId);
		driverInfo.setStatus(userStatus);
		driverInfo.setName(driverName);
		driverInfo.setSurname(driverSurname);

		SpaceInfo spaceInfo = new SpaceInfo();
		spaceInfo.setSpaceId(spaceId);
		spaceInfo.setAddress(spaceAddress);
		spaceInfo.setStatus(spaceStatus);
		spaceInfo.setBatchId(batchId);
		spaceInfo.setDescription(spaceDescription);
		spaceInfo.setTitle(spaceTitle);
		OwnerInfo ownerInfo = new OwnerInfo();
		ownerInfo.setOwnerId(spaceOwnerId);
		ownerInfo.setStatus(userStatus);
		ownerInfo.setName(spaceOwnerName);
		ownerInfo.setSurname(spaceOwnerSurname);
		spaceInfo.setOwner(ownerInfo);
		SpaceLocation spaceLocation = new SpaceLocation();
		spaceLocation.setDistrict(spaceDistrict);
		spaceLocation.setTown(spaceTown);
		spaceLocation.setStateName(spaceStateName);
		spaceLocation.setPosition(spacePosition);
		spaceInfo.setSpaceLocation(spaceLocation);

		BookingInitiationModel bookingInitiationModel = new BookingInitiationModel();
		bookingInitiationModel.setDriverId(driverId);
		bookingInitiationModel.setAmount(amount);
		bookingInitiationModel.setBatchId(batchId);
		bookingInitiationModel.setOwnerId(spaceOwnerId);

		when(driverOperationService.retrieveDriver(driverId)).thenReturn(driverInfo);
		when(spaceOperationService.retrieveSpace(batchId, spaceStatus)).thenReturn(Collections.singletonList(spaceInfo));
		when(bookingOperationService.initiateBookingRequest(bookingInitiationModel)).thenReturn(bookingTrackingCode);

		DriverSpaceBookingResult result = service.bookSpace(model);

		assertThat(result).isNotNull();
		assertThat(result.getSpaceId()).isNotNull();
		assertThat(result.getSpaceId()).isEqualTo(spaceId);
		assertThat(result.getBatchId()).isNotNull();
		assertThat(result.getBatchId()).isEqualTo(batchId);
		assertThat(result.getDescription()).isNotNull();
		assertThat(result.getDescription()).isEqualTo(spaceDescription);
		assertThat(result.getTitle()).isNotNull();
		assertThat(result.getTitle()).isEqualTo(spaceTitle);
		assertThat(result.getAddress()).isNotNull();
		assertThat(result.getAddress()).isEqualTo(spaceAddress);
		assertThat(result.getOwner()).isNotNull();
		assertThat(result.getOwner().getStatus()).isEqualTo(userStatus);
		assertThat(result.getOwner().getOwnerId()).isEqualTo(spaceOwnerId);
		assertThat(result.getOwner().getName()).isEqualTo(spaceOwnerName);
		assertThat(result.getOwner().getSurname()).isEqualTo(spaceOwnerSurname);
		assertThat(result.getTrackingCode()).isNotNull();
		assertThat(result.getTrackingCode()).isEqualTo(bookingTrackingCode);

	}


	@TestConfiguration
	@ComponentScan(
			basePackageClasses = { DriverSpaceOperationService.class, DriverSpaceOperationMapper.class,
					ParameterValidator.class, LocationOperationStrategy.class,
					DistrictBasedLocationOperationService.class, StateGeoOperationService.class,
					DriverOperationService.class, DriverOperationServiceMapper.class, SpaceOperationService.class,
					SpaceOperationServiceMapper.class, OwnerOperationService.class, OwnerOperationServiceMapper.class,
					BookingOperationService.class, BookingOperationServiceMapper.class,
					TrackingCodeOperationService.class, TrackingCodeProperties.class,
					StateGeoOperationServiceMapper.class
			},
			useDefaultFilters = false,
			includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
					classes = { DriverSpaceOperationService.class, DriverSpaceOperationMapper.class,
							ParameterValidator.class, LocationOperationStrategy.class,
							DistrictBasedLocationOperationService.class, StateGeoOperationService.class,
							DriverOperationService.class, DriverOperationServiceMapper.class,
							SpaceOperationService.class, SpaceOperationServiceMapper.class, OwnerOperationService.class,
							OwnerOperationServiceMapper.class, BookingOperationService.class,
							BookingOperationServiceMapper.class, TrackingCodeOperationService.class,
							TrackingCodeProperties.class, StateGeoOperationServiceMapper.class }))

	static class TestConfigs {

	}
}