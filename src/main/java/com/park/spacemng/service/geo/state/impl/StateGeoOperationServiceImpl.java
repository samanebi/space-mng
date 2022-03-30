package com.park.spacemng.service.geo.state.impl;

import com.park.spacemng.model.geo.state.dao.StateDao;
import com.park.spacemng.service.geo.state.StateGeoOperationService;
import com.park.spacemng.service.geo.state.mapper.StateGeoOperationServiceMapper;
import com.park.spacemng.service.geo.state.model.StateRetrievalModel;
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

	//todo : complete repo
	@Override
	public StateRetrievalResult retrieveStateDetails(StateRetrievalModel model) {
		return new StateRetrievalResult(mapper.toStateDetails(stateDao.findStateByCode(model.getStateName())));
	}

}