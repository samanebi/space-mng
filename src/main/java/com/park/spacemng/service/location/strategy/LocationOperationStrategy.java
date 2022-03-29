package com.park.spacemng.service.location.strategy;

import com.park.spacemng.model.constants.LocationSelectionType;
import com.park.spacemng.service.location.LocationOperationService;

public sealed interface LocationOperationStrategy permits LocationOperationStrategyImpl {

	LocationOperationService getLocationOperationService(LocationSelectionType type);

}