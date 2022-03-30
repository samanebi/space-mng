package com.park.spacemng.service.space.owner.impl;

import com.park.spacemng.service.space.owner.OwnerSpaceOperationService;
import com.park.spacemng.service.space.owner.model.OwnerSpaceRetrievalModel;
import com.park.spacemng.service.space.owner.model.OwnerSpaceRetrievalResult;
import com.park.spacemng.service.space.owner.model.SpaceGenerationModel;
import com.park.spacemng.service.space.owner.model.SpaceRequestsResolutionModel;
import com.park.spacemng.service.space.owner.model.SpaceRequestsRetrievalModel;
import com.park.spacemng.service.space.owner.model.SpaceRequestsRetrievalResult;
import com.park.spacemng.service.space.owner.model.SpaceUpdateModel;

public class OwnerSpaceOperationServiceImpl implements OwnerSpaceOperationService {

	@Override
	public void generateSpaces(SpaceGenerationModel model) {
	}

	@Override
	public void updateSpace(SpaceUpdateModel model) {
	}

	@Override
	public SpaceRequestsRetrievalResult getSpaceRequests(SpaceRequestsRetrievalModel model) {
		return null;
	}

	@Override
	public void resolveSpaceRequests(SpaceRequestsResolutionModel model) {
	}

	@Override
	public OwnerSpaceRetrievalResult querySpaces(OwnerSpaceRetrievalModel model) {
		return null;
	}

}