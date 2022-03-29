package com.park.spacemng.service.space.driver.mapper;

import com.park.spacemng.service.location.model.DesiredLocationRetrievalModel;
import com.park.spacemng.service.space.driver.model.NearbyAvailableSpacesRetrievalModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DriverSpaceOperationMapper {

	DesiredLocationRetrievalModel toDesiredLocationRetrievalModel(NearbyAvailableSpacesRetrievalModel model);

}