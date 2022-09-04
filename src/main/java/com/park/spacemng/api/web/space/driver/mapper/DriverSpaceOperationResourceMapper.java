package com.park.spacemng.api.web.space.driver.mapper;

import com.park.spacemng.model.booking.BookingRequest;
import com.park.spacemng.model.constants.ProcessStatus;
import com.park.spacemng.model.dto.BookingRequestDetailsDto;
import com.park.spacemng.model.dto.LocationDto;
import com.park.spacemng.model.dto.OwnerDto;
import com.park.spacemng.model.dto.SpaceDetailsDto;
import com.park.spacemng.model.request.NearbyAvailableSpacesRequest;
import com.park.spacemng.model.request.SpaceBookingRequest;
import com.park.spacemng.model.request.SpaceResolutionRequestDetails;
import com.park.spacemng.model.response.BookingRequestRetrievalResponse;
import com.park.spacemng.model.response.NearbyAvailableSpacesResponse;
import com.park.spacemng.model.response.SpaceBookingResponse;
import com.park.spacemng.service.space.driver.model.DriverLocation;
import com.park.spacemng.service.space.driver.model.DriverSpaceBookingModel;
import com.park.spacemng.service.space.driver.model.DriverSpaceBookingResult;
import com.park.spacemng.service.space.driver.model.NearbyAvailableSpacesRetrievalModel;
import com.park.spacemng.service.space.driver.model.NearbyAvailableSpacesRetrievalResult;
import com.park.spacemng.service.space.driver.model.OwnerDetails;
import com.park.spacemng.service.space.driver.model.SpaceDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = { ProcessStatus.class })
public interface DriverSpaceOperationResourceMapper {

	NearbyAvailableSpacesRetrievalModel toNearbyAvailableSpacesRetrievalModel(NearbyAvailableSpacesRequest request);

	@Mapping(target = "status", expression = "java(ProcessStatus.SUCCESS)")
	NearbyAvailableSpacesResponse toNearbyAvailableSpacesResponse(NearbyAvailableSpacesRetrievalResult result);

	@Mapping(target = "batchId", source = "batchId")
	@Mapping(target = "driverId", source = "driverId")
	DriverSpaceBookingModel toDriverBookingModel(SpaceBookingRequest request);

	default SpaceBookingResponse toBookingSpaceResponse(DriverSpaceBookingResult result) {
		SpaceBookingResponse spaceBookingResponse = new SpaceBookingResponse(toSpaceDetailsDto(result));
		spaceBookingResponse.setTrackingCode(result.getTrackingCode());
		return spaceBookingResponse;
	}

	@Mapping(target = "location.position", source = "position")
	SpaceDetailsDto toSpaceDetailsDto(SpaceDetails space);

	OwnerDto toOwnerDto(OwnerDetails owner);

	SpaceDetailsDto toSpaceDetailsDto(DriverSpaceBookingResult result);

	LocationDto toLocationDto(DriverLocation driverLocation);

	BookingRequestDetailsDto toBookingRequestDetailsDto(BookingRequest request);

	default BookingRequestRetrievalResponse toBookingRequestRetrievalResponse(BookingRequest request) {
		BookingRequestRetrievalResponse response = new BookingRequestRetrievalResponse();
		response.setDetails(toBookingRequestDetailsDto(request));
		return response;
	}

}