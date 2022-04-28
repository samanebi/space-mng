package com.park.spacemng.service.geo.state;

import com.park.spacemng.exception.StateNotFoundException;
import com.park.spacemng.service.geo.state.model.StateRetrievalModel;
import com.park.spacemng.service.geo.state.model.StateRetrievalResult;

public interface StateGeoOperationService {

	StateRetrievalResult retrieveStateDetails(StateRetrievalModel model) throws StateNotFoundException;

}