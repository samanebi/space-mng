package com.park.spacemng.service.location.strategy;

import com.park.spacemng.model.constants.LocationSelectionType;
import com.park.spacemng.service.location.LocationOperationService;

public interface LocationOperationStrategy {

	LocationOperationService getLocationOperationService(LocationSelectionType type);

}