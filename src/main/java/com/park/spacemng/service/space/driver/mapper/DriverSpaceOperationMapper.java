package com.park.spacemng.service.space.driver.mapper;

import com.park.spacemng.model.space.Space;
import com.park.spacemng.model.user.Owner;
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
import com.park.spacemng.service.space.space.model.SpaceTakeUnderProcessModel;
import com.park.spacemng.service.user.owner.model.OwnerInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DriverSpaceOperationMapper {

	DesiredLocationRetrievalModel toDesiredLocationRetrievalModel(NearbyAvailableSpacesRetrievalModel model);

	default OwnerSpaceRetrievalModel toOwnerSpaceRetrievalModel(DesiredLocationRetrievalResult result) {
		return toOwnerSpaceRetrievalModel(result.getLocation());
	}

	OwnerSpaceRetrievalModel toOwnerSpaceRetrievalModel(DesiredLocation location);

	SpaceDetails toSpaceDetails(Space space);

	OwnerDetails toOwnerDetails(Owner owner);

	SpaceTakeUnderProcessModel toSpaceTakeUnderProcessModel(SpaceInfo space);

	BookingInitiationModel toBookingInitiationModel(DriverSpaceBookingModel model, String ownerId);

	DriverSpaceBookingResult toDriverSpaceBookingResult(SpaceInfo info, String trckingCode);

	OwnerDetails toOwnerDetails(OwnerInfo ownerInfo);

}