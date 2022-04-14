package com.park.spacemng.service.space.owner;

import com.park.spacemng.exception.ParameterValidationException;
import com.park.spacemng.service.space.owner.model.OwnerSpaceGenerationModel;
import com.park.spacemng.service.space.owner.model.OwnerSpaceRetrievalModel;
import com.park.spacemng.service.space.owner.model.OwnerSpaceRetrievalResult;
import com.park.spacemng.service.space.owner.model.OwnerSpaceUpdateModel;
import com.park.spacemng.service.space.owner.model.SpaceRequestsResolutionModel;
import com.park.spacemng.service.space.owner.model.SpaceRequestsRetrievalResult;

public interface OwnerSpaceOperationService {

	void generateSpaces(OwnerSpaceGenerationModel model) throws ParameterValidationException;

	void updateSpace(OwnerSpaceUpdateModel model);

	SpaceRequestsRetrievalResult getSpaceRequests(String batchId) throws ParameterValidationException;

	void resolveSpaceRequests(SpaceRequestsResolutionModel model) throws ParameterValidationException;

	OwnerSpaceRetrievalResult querySpaces(OwnerSpaceRetrievalModel model);

}