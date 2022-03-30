package com.park.spacemng.service.space.driver.mapper;

import com.park.spacemng.model.space.Space;
import com.park.spacemng.service.location.model.DesiredLocationRetrievalModel;
import com.park.spacemng.service.location.model.DesiredLocationRetrievalResult;
import com.park.spacemng.service.space.driver.model.NearbyAvailableSpacesRetrievalModel;
import com.park.spacemng.service.space.driver.model.SpaceDetails;
import com.park.spacemng.service.space.owner.model.OwnerSpaceRetrievalModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DriverSpaceOperationMapper {

	DesiredLocationRetrievalModel toDesiredLocationRetrievalModel(NearbyAvailableSpacesRetrievalModel model);

	OwnerSpaceRetrievalModel toOwnerSpaceRetrievalModel(DesiredLocationRetrievalResult result);

	SpaceDetails toSpaceDetails(Space space);

}