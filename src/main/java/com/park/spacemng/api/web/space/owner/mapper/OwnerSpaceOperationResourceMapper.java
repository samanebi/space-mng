package com.park.spacemng.api.web.space.owner.mapper;

import java.util.List;

import com.park.spacemng.model.request.LocationRequest;
import com.park.spacemng.model.request.SpaceResolutionRequestDetails;
import com.park.spacemng.model.request.SpaceGenerationRequest;
import com.park.spacemng.model.request.SpaceResolutionRequest;
import com.park.spacemng.model.request.SpaceUpdateRequest;
import com.park.spacemng.model.response.SpaceRetrievalResponse;
import com.park.spacemng.model.space.SpaceLocation;
import com.park.spacemng.service.booking.model.BookingRequestDetails;
import com.park.spacemng.service.booking.model.BookingRequestsRetrievalResult;
import com.park.spacemng.service.space.owner.model.OwnerSpaceGenerationModel;
import com.park.spacemng.service.space.owner.model.OwnerSpaceRetrievalModel;
import com.park.spacemng.service.space.owner.model.OwnerSpaceUpdateModel;
import com.park.spacemng.service.space.owner.model.SpaceBookingModel;
import com.park.spacemng.service.space.owner.model.SpaceRequestsResolutionModel;
import com.park.spacemng.service.space.owner.model.SpaceRequestsRetrievalResult;
import com.park.spacemng.service.space.space.model.SpaceGenerationModel;
import com.park.spacemng.service.space.space.model.SpaceQueryModel;
import com.park.spacemng.service.space.space.model.SpaceUpdateModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OwnerSpaceOperationResourceMapper {

	SpaceRequestsResolutionModel toSpaceRequestsResolutionModel(SpaceResolutionRequest request, String userId,
			String batchId);

	@Mapping(source = "userId", target = "ownerId")
	@Mapping(source = "request.location", target = "spaceLocation")
	OwnerSpaceUpdateModel toSpaceUpdateModel(SpaceUpdateRequest request, String userId);

	SpaceRetrievalResponse toSpaceRetrievalResponse(SpaceRequestsRetrievalResult result);

	@Mapping(source = "userId", target = "ownerId")
	@Mapping(source = "request.location", target = "spaceLocation")
	OwnerSpaceGenerationModel toSpaceGenerationModel(SpaceGenerationRequest request, String userId);

	@InheritInverseConfiguration
	default SpaceLocation toLocation(LocationRequest location) {
		SpaceLocation spaceLocation = new SpaceLocation();
		spaceLocation.setPosition(location.getLocation());
		spaceLocation.setDistrict(location.getDistrict());
		spaceLocation.setTown(location.getTown());
		spaceLocation.setStateName(location.getStateName());
		return spaceLocation;
	}

	SpaceBookingModel toSpaceBookingModel(SpaceResolutionRequestDetails request);

	SpaceGenerationModel toSpaceGenerationModel(OwnerSpaceGenerationModel model);

	SpaceUpdateModel toSpaceUpdateModel(OwnerSpaceUpdateModel model);

	SpaceRequestsRetrievalResult toSpaceRequestsRetrievalResult(BookingRequestsRetrievalResult result);

	BookingRequestDetails toBookingRequestDetails(SpaceBookingModel model);

	List<BookingRequestDetails> toBookingRequestDetailsList(List<SpaceBookingModel> models);

	SpaceQueryModel toSpaceQueryModel(OwnerSpaceRetrievalModel model);

}