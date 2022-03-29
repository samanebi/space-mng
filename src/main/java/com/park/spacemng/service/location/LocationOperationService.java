package com.park.spacemng.service.location;

import com.park.spacemng.model.constants.LocationSelectionType;
import com.park.spacemng.service.location.model.DesiredLocationRetrievalModel;
import com.park.spacemng.service.location.model.DesiredLocationRetrievalResult;

public interface LocationOperationService {

	DesiredLocationRetrievalResult getDesiredLocation(DesiredLocationRetrievalModel model);

	LocationSelectionType getType();

}