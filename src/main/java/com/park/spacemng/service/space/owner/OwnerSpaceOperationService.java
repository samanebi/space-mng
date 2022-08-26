package com.park.spacemng.service.space.owner;

import com.park.spacemng.exception.GeneralException;
import com.park.spacemng.exception.ParameterValidationException;
import com.park.spacemng.service.space.owner.model.OwnerSpaceGenerationModel;
import com.park.spacemng.service.space.owner.model.OwnerSpaceRetrievalModel;
import com.park.spacemng.service.space.owner.model.OwnerSpaceRetrievalResult;
import com.park.spacemng.service.space.owner.model.OwnerSpaceUpdateModel;
import com.park.spacemng.service.space.owner.model.SpaceRequestsResolutionModel;
import com.park.spacemng.service.space.owner.model.SpaceRequestsRetrievalResult;

import org.springframework.data.geo.Point;

import java.util.Map;

public interface OwnerSpaceOperationService {

	void generateSpaces(OwnerSpaceGenerationModel model) throws GeneralException;

	void updateSpace(OwnerSpaceUpdateModel model) throws GeneralException;

	SpaceRequestsRetrievalResult getSpaceRequests(String batchId) throws ParameterValidationException;

	Map<String, Integer> resolveSpaceRequests(SpaceRequestsResolutionModel model) throws ParameterValidationException;

	OwnerSpaceRetrievalResult querySpaces(OwnerSpaceRetrievalModel model);

	OwnerSpaceRetrievalResult findSpaces(Point point);

}