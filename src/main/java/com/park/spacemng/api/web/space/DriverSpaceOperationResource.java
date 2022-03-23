package com.park.spacemng.api.web.space;

import java.util.Collections;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.park.spacemng.model.request.NearbyAvailableSpacesRequest;
import com.park.spacemng.model.request.SpaceAllocationRequest;
import com.park.spacemng.model.response.BookingSpaceResponse;
import com.park.spacemng.model.response.NearbyAvailableSpacesResponse;
import com.park.spacemng.util.Constants;
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
@RequestMapping("/spaces")
public class DriverSpaceOperationResource {

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<NearbyAvailableSpacesResponse> getNearbyAvailableSpaces(
			@Valid @RequestBody NearbyAvailableSpacesRequest request,
			@NotBlank @RequestHeader(Constants.HEADER_USER_ID) String userId) {
		return new ResponseEntity<>(new NearbyAvailableSpacesResponse(Collections.emptyList()), HttpStatus.OK);
	}

	@PostMapping(value = "/allocate",
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BookingSpaceResponse> allocateSpace(@NotNull @RequestBody SpaceAllocationRequest request,
			@NotBlank @RequestHeader(Constants.HEADER_USER_ID) String userId) {
		return new ResponseEntity<>(new BookingSpaceResponse(), HttpStatus.OK);
	}

}