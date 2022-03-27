package com.park.spacemng.api.web.space.owner.mapper;

import com.park.spacemng.model.request.SpaceGenerationRequest;
import com.park.spacemng.model.request.SpaceResolutionRequest;
import com.park.spacemng.model.request.SpaceUpdateRequest;
import com.park.spacemng.model.response.SpaceRetrievalResponse;
import com.park.spacemng.service.space.owner.model.SpaceGenerationModel;
import com.park.spacemng.service.space.owner.model.SpaceRequestsResolutionModel;
import com.park.spacemng.service.space.owner.model.SpaceRequestsRetrievalModel;
import com.park.spacemng.service.space.owner.model.SpaceRequestsRetrievalResult;
import com.park.spacemng.service.space.owner.model.SpaceUpdateModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OwnerSpaceOperationResourceMapper {

	SpaceRequestsResolutionModel toSpaceRequestsResolutionModel(SpaceResolutionRequest request, String userId,
			String batchId);

	SpaceUpdateModel toSpaceUpdateModel(SpaceUpdateRequest request, String userId);

	SpaceRequestsRetrievalModel toSpaceRequestsRetrievalModel(String userId, String batchId);

	SpaceRetrievalResponse toSpaceRetrievalResponse(SpaceRequestsRetrievalResult result);

	SpaceGenerationModel toSpaceGenerationModel(SpaceGenerationRequest request, String userId);

}