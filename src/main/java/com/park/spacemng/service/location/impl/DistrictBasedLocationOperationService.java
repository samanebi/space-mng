package com.park.spacemng.service.location.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.park.spacemng.model.constants.District;
import com.park.spacemng.model.constants.LocationSelectionType;
import com.park.spacemng.service.geo.state.StateGeoOperationService;
import com.park.spacemng.service.geo.state.model.StateRetrievalModel;
import com.park.spacemng.service.geo.state.model.StateRetrievalResult;
import com.park.spacemng.service.location.LocationOperationService;
import com.park.spacemng.service.location.model.DesiredLocation;
import com.park.spacemng.service.location.model.DesiredLocationRetrievalModel;
import com.park.spacemng.service.location.model.DesiredLocationRetrievalResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Primary;
import org.springframework.data.geo.Point;
import org.springframework.data.geo.Polygon;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Primary
@RequiredArgsConstructor
public class DistrictBasedLocationOperationService implements LocationOperationService {

	private final StateGeoOperationService stateGeoOperationService;

	@Override
	public DesiredLocationRetrievalResult getDesiredLocation(DesiredLocationRetrievalModel model) {
		DesiredLocation desiredLocation = new DesiredLocation();
		desiredLocation.setStateName(Collections.singletonList(model.getStateName()));
		desiredLocation.setTown(Collections.singletonList(model.getTown()));
		desiredLocation.setDistricts(getDistrictByGeographicLocation(model));
		return new DesiredLocationRetrievalResult(desiredLocation);
	}

	@Override
	public LocationSelectionType getType() {
		return LocationSelectionType.DISTRICT_BASED;
	}

	private List<District> getDistrictByGeographicLocation(DesiredLocationRetrievalModel model) {
		StateRetrievalResult stateRetrievalResult = stateGeoOperationService
				.retrieveStateDetails(new StateRetrievalModel(model.getStateName()));
		List<District> result = new ArrayList<>();
		stateRetrievalResult.getStateDetails().getDistricts().forEach((name, area) -> {
			if (isPointInsideArea(model.getLocation(), area)) {
				result.add(name);
			}
		});
		return result;
	}

	private boolean isPointInsideArea(Point point, Polygon polygon) {
		var first = point.getX();
		var second = point.getY();

		var inside = false;
		for (int firstCounter = 0, secondCounter = polygon.getPoints().size() - 1;
			 firstCounter < polygon.getPoints().size(); secondCounter = firstCounter++) {
			double xi = polygon.getPoints().get(firstCounter).getX();
			double yi = polygon.getPoints().get(firstCounter).getY();
			double xj = polygon.getPoints().get(secondCounter).getX();
			double yj = polygon.getPoints().get(secondCounter).getY();
			boolean intersect = ((yi > second) != (yj > second))
					&& (first < (xj - xi) * (second - yi) / (yj - yi) + xi);
			if (intersect) {
				inside = !inside;
			}
		}
		return inside;
	}
}