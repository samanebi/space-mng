package com.park.spacemng.service.location.impl;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

import com.park.spacemng.exception.StateNotFoundException;
import com.park.spacemng.model.constants.District;
import com.park.spacemng.model.constants.StateName;
import com.park.spacemng.model.constants.Town;
import com.park.spacemng.service.geo.state.StateGeoOperationService;
import com.park.spacemng.service.geo.state.model.StateDetails;
import com.park.spacemng.service.geo.state.model.StateRetrievalResult;
import com.park.spacemng.service.location.model.DesiredLocationRetrievalModel;
import com.park.spacemng.service.location.model.DesiredLocationRetrievalResult;
import com.park.spacemng.service.space.owner.OwnerSpaceOperationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.data.geo.Point;
import org.springframework.data.geo.Polygon;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith({ MockitoExtension.class })
@ContextConfiguration
class DistrictBasedSpaceLocationOperationServiceTest {

	DistrictBasedLocationOperationService service;

	@Mock
	StateGeoOperationService stateGeoOperationService;

	@Mock
	OwnerSpaceOperationService ownerSpaceOperationService;

	@BeforeEach
	void beforeEach() {
		service = new DistrictBasedLocationOperationService(stateGeoOperationService);
	}

	@Test
	void getDesiredLocation_locationInDistrict_success() throws StateNotFoundException {
		StateRetrievalResult result = new StateRetrievalResult();
		StateDetails stateDetails = new StateDetails();
		stateDetails.setState(StateName.TEHRAN);
		Map<District, Polygon> districts = new EnumMap<>(District.class);
		Point point1 = new Point(0.0, 0.0);
		Point point2 = new Point(100.0, 0.0);
		Point point3 = new Point(0.0, 100.0);
		Point point4 = new Point(100.0, 100.0);
		Polygon polygon = new Polygon(Arrays.asList(point1, point2, point3, point4));
		districts.put(District.ONE, polygon);
		stateDetails.setDistricts(districts);
		result.setStateDetails(stateDetails);

		DesiredLocationRetrievalModel model = new DesiredLocationRetrievalModel();
		model.setLocation(new Point(10.0, 10.0));
		model.setTown(Town.TEHRAN);
		model.setStateName(StateName.TEHRAN);

		Mockito.when(stateGeoOperationService.retrieveStateDetails(StateName.TEHRAN)).thenReturn(result);
		DesiredLocationRetrievalResult desiredLocation = service.getDesiredLocation(model);

		assertThat(desiredLocation.getLocation().getDistricts()).contains(District.ONE);
		assertThat(desiredLocation.getLocation().getStates()).contains(StateName.TEHRAN);
		assertThat(desiredLocation.getLocation().getTowns()).contains(Town.TEHRAN);
	}

	@Test
	void getDesiredLocation_locationNotInDistrict_success() throws StateNotFoundException {
		StateRetrievalResult result = new StateRetrievalResult();
		StateDetails stateDetails = new StateDetails();
		stateDetails.setState(StateName.TEHRAN);
		Map<District, Polygon> districts = new EnumMap<>(District.class);
		Point point1 = new Point(0.0, 0.0);
		Point point2 = new Point(100.0, 0.0);
		Point point3 = new Point(0.0, 100.0);
		Point point4 = new Point(100.0, 100.0);
		Polygon polygon = new Polygon(Arrays.asList(point1, point2, point3, point4));
		districts.put(District.ONE, polygon);
		stateDetails.setDistricts(districts);
		result.setStateDetails(stateDetails);

		DesiredLocationRetrievalModel model = new DesiredLocationRetrievalModel();
		model.setLocation(new Point(200.0, 200.0));
		model.setTown(Town.TEHRAN);
		model.setStateName(StateName.TEHRAN);

		Mockito.when(stateGeoOperationService.retrieveStateDetails(StateName.TEHRAN)).thenReturn(result);
		DesiredLocationRetrievalResult desiredLocation = service.getDesiredLocation(model);

		assertThat(desiredLocation.getLocation().getDistricts()).isEmpty();
		assertThat(desiredLocation.getLocation().getStates()).contains(StateName.TEHRAN);
		assertThat(desiredLocation.getLocation().getTowns()).contains(Town.TEHRAN);
	}

}