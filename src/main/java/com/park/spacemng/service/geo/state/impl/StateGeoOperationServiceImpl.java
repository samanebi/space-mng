package com.park.spacemng.service.geo.state.impl;

import com.park.spacemng.exception.StateNotFoundException;
import com.park.spacemng.model.constants.StateName;
import com.park.spacemng.model.geo.state.dao.StateDao;
import com.park.spacemng.service.geo.state.StateGeoOperationService;
import com.park.spacemng.service.geo.state.mapper.StateGeoOperationServiceMapper;
import com.park.spacemng.service.geo.state.model.StateRetrievalResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class StateGeoOperationServiceImpl implements StateGeoOperationService {

	private final StateDao stateDao;

	private final StateGeoOperationServiceMapper mapper;

	@Override
	public StateRetrievalResult retrieveStateDetails(StateName stateName) throws StateNotFoundException {
		return new StateRetrievalResult(mapper.toStateDetails(stateDao.findByStateName(stateName)
				.orElseThrow(() -> new StateNotFoundException("state not found : " + stateName))));
	}

}