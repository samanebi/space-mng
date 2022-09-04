package com.park.spacemng.service.space.driver.mapper;

import com.park.spacemng.model.space.space.Space;
import com.park.spacemng.model.user.owner.Owner;
import com.park.spacemng.service.booking.model.BookingInitiationModel;
import com.park.spacemng.service.location.model.DesiredLocation;
import com.park.spacemng.service.location.model.DesiredLocationRetrievalModel;
import com.park.spacemng.service.location.model.DesiredLocationRetrievalResult;
import com.park.spacemng.service.space.driver.model.DriverSpaceBookingModel;
import com.park.spacemng.service.space.driver.model.DriverSpaceBookingResult;
import com.park.spacemng.service.space.driver.model.NearbyAvailableSpacesRetrievalModel;
import com.park.spacemng.service.space.driver.model.OwnerDetails;
import com.park.spacemng.service.space.driver.model.SpaceDetails;
import com.park.spacemng.service.space.owner.model.OwnerSpaceRetrievalModel;
import com.park.spacemng.service.space.space.model.SpaceInfo;
import com.park.spacemng.service.user.owner.model.OwnerInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DriverSpaceOperationMapper {

	DesiredLocationRetrievalModel toDesiredLocationRetrievalModel(NearbyAvailableSpacesRetrievalModel model);

	default OwnerSpaceRetrievalModel toOwnerSpaceRetrievalModel(DesiredLocationRetrievalResult result) {
		return toOwnerSpaceRetrievalModel(result.getLocation());
	}

	OwnerSpaceRetrievalModel toOwnerSpaceRetrievalModel(DesiredLocation location);

	@Mapping(target = "spaceId", source = "id")
	@Mapping(target = "position", source = "position")
	SpaceDetails toSpaceDetails(Space space);

	@Mapping(target = "ownerId", source = "id")
	OwnerDetails toOwnerDetails(Owner owner);

	@Mapping(target = "batchId", source = "model.batchId")
	@Mapping(target = "spaceId", source = "info.spaceId")
	@Mapping(target = "ownerId", source = "info.owner.ownerId")
	@Mapping(target = "driverId", source = "model.driverId")
	BookingInitiationModel toBookingInitiationModel(DriverSpaceBookingModel model, SpaceInfo info);

	DriverSpaceBookingResult toDriverSpaceBookingResult(SpaceInfo info, String trackingCode);

	OwnerDetails toOwnerDetails(OwnerInfo ownerInfo);

}