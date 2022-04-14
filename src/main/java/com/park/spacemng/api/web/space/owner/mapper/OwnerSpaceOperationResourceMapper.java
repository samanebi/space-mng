package com.park.spacemng.api.web.space.owner.mapper;

import com.park.spacemng.model.request.LocationRequest;
import com.park.spacemng.model.request.SpaceBookingRequest;
import com.park.spacemng.model.request.SpaceGenerationRequest;
import com.park.spacemng.model.request.SpaceResolutionRequest;
import com.park.spacemng.model.request.SpaceUpdateRequest;
import com.park.spacemng.model.response.SpaceRetrievalResponse;
import com.park.spacemng.service.booking.model.BookingRequestsRetrievalResult;
import com.park.spacemng.service.space.owner.model.Location;
import com.park.spacemng.service.space.owner.model.OwnerSpaceGenerationModel;
import com.park.spacemng.service.space.owner.model.OwnerSpaceUpdateModel;
import com.park.spacemng.service.space.owner.model.SpaceBookingModel;
import com.park.spacemng.service.space.owner.model.SpaceRequestsResolutionModel;
import com.park.spacemng.service.space.owner.model.SpaceRequestsRetrievalResult;
import com.park.spacemng.service.space.space.model.SpaceGenerationModel;
import com.park.spacemng.service.space.space.model.SpaceUpdateModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OwnerSpaceOperationResourceMapper {

	SpaceRequestsResolutionModel toSpaceRequestsResolutionModel(SpaceResolutionRequest request, String userId,
			String batchId);

	OwnerSpaceUpdateModel toSpaceUpdateModel(SpaceUpdateRequest request, String userId);

	SpaceRetrievalResponse toSpaceRetrievalResponse(SpaceRequestsRetrievalResult result);

	OwnerSpaceGenerationModel toSpaceGenerationModel(SpaceGenerationRequest request, String userId);

	@InheritInverseConfiguration
	Location toLocation(LocationRequest location);

	SpaceBookingModel toSpaceBookingModel(SpaceBookingRequest request);

	SpaceGenerationModel toSpaceGenerationModel(OwnerSpaceGenerationModel model);

	SpaceUpdateModel toSpaceUpdateModel(OwnerSpaceUpdateModel model);

	SpaceRequestsRetrievalResult toSpaceRequestsRetrievalResult(BookingRequestsRetrievalResult result);

}