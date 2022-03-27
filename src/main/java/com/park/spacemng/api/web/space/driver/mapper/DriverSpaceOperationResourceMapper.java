package com.park.spacemng.api.web.space.driver.mapper;

import com.park.spacemng.model.dto.OwnerDto;
import com.park.spacemng.model.dto.SpaceDetailsDto;
import com.park.spacemng.model.request.LocationRequest;
import com.park.spacemng.model.request.NearbyAvailableSpacesRequest;
import com.park.spacemng.model.request.SpaceBookingRequest;
import com.park.spacemng.model.response.NearbyAvailableSpacesResponse;
import com.park.spacemng.model.response.SpaceBookingResponse;
import com.park.spacemng.service.space.driver.model.DriverSpaceBookingModel;
import com.park.spacemng.service.space.driver.model.DriverSpaceBookingResult;
import com.park.spacemng.service.space.driver.model.Location;
import com.park.spacemng.service.space.driver.model.NearbyAvailableSpacesRetrievalModel;
import com.park.spacemng.service.space.driver.model.NearbyAvailableSpacesRetrievalResult;
import com.park.spacemng.service.space.driver.model.OwnerDetails;
import com.park.spacemng.service.space.driver.model.SpaceDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DriverSpaceOperationResourceMapper {

	NearbyAvailableSpacesRetrievalModel toNearbyAvailableSpacesRetrievalModel(NearbyAvailableSpacesRequest request);

	NearbyAvailableSpacesResponse toNearbyAvailableSpacesResponse(NearbyAvailableSpacesRetrievalResult result);

	DriverSpaceBookingModel toDriverBookingModel(SpaceBookingRequest request, String userId);

	default SpaceBookingResponse toBookingSpaceResponse(DriverSpaceBookingResult result) {
		return new SpaceBookingResponse(toSpaceDetailsDto(result));
	}

	Location toLocation(LocationRequest request);

	SpaceDetailsDto toSpaceDetailsDto(SpaceDetails space);

	OwnerDto toOwnerDto(OwnerDetails owner);

	SpaceDetailsDto toSpaceDetailsDto(DriverSpaceBookingResult result);

}