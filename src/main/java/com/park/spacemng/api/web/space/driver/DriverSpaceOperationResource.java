package com.park.spacemng.api.web.space.driver;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.park.spacemng.api.web.space.driver.mapper.DriverSpaceOperationResourceMapper;
import com.park.spacemng.exception.GeneralException;
import com.park.spacemng.model.request.NearbyAvailableSpacesRequest;
import com.park.spacemng.model.request.SpaceBookingRequest;
import com.park.spacemng.model.response.NearbyAvailableSpacesResponse;
import com.park.spacemng.model.response.SpaceBookingResponse;
import com.park.spacemng.service.space.driver.DriverSpaceOperationService;
import com.park.spacemng.service.space.driver.model.DriverSpaceBookingResult;
import com.park.spacemng.service.space.driver.model.NearbyAvailableSpacesRetrievalResult;
import com.park.spacemng.util.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/spaces")
public class DriverSpaceOperationResource {

	private final DriverSpaceOperationService service;

	private final DriverSpaceOperationResourceMapper mapper;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<NearbyAvailableSpacesResponse> getNearbyAvailableSpaces(
			@Valid @RequestBody NearbyAvailableSpacesRequest request) throws GeneralException {
		NearbyAvailableSpacesRetrievalResult response =
				service.getNearbyAvailableSpaces(mapper.toNearbyAvailableSpacesRetrievalModel(request));
		return new ResponseEntity<>(mapper.toNearbyAvailableSpacesResponse(response), HttpStatus.OK);
	}

	@PostMapping(value = "/book",
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SpaceBookingResponse> bookSpace(@NotNull @RequestBody SpaceBookingRequest request,
			@NotBlank @RequestHeader(Constants.HEADER_USER_ID) String userId) throws GeneralException {
		DriverSpaceBookingResult response = service.bookSpace(mapper.toDriverBookingModel(request, userId));
		return new ResponseEntity<>(mapper.toBookingSpaceResponse(response), HttpStatus.OK);
	}

}