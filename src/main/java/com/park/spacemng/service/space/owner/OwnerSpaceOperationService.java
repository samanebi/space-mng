package com.park.spacemng.service.space.owner;

import com.park.spacemng.service.space.owner.model.SpaceGenerationModel;
import com.park.spacemng.service.space.owner.model.SpaceRequestsResolutionModel;
import com.park.spacemng.service.space.owner.model.SpaceRequestsRetrievalModel;
import com.park.spacemng.service.space.owner.model.SpaceRequestsRetrievalResult;
import com.park.spacemng.service.space.owner.model.SpaceUpdateModel;

public interface OwnerSpaceOperationService {

	void generateSpaces(SpaceGenerationModel model);

	void updateSpace(SpaceUpdateModel model);

	SpaceRequestsRetrievalResult getSpaceRequests(SpaceRequestsRetrievalModel model);

	void resolveSpaceRequests(SpaceRequestsResolutionModel model);

}