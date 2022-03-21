package com.park.spacemng.api.web;

import java.util.Collections;

import com.park.spacemng.model.response.BookingSpaceResponse;
import com.park.spacemng.model.response.NearbyAvailableSpacesResponse;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Validated
@RequestMapping("/spaces")
public class SpaceOperationResource {

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<NearbyAvailableSpacesResponse> getNearbyAvailableSpaces() {
		return new ResponseEntity<>(new NearbyAvailableSpacesResponse(Collections.emptyList()), HttpStatus.OK);
	}

	@PostMapping(value = "/book",
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BookingSpaceResponse> bookSpace() {
		return new ResponseEntity<>(new BookingSpaceResponse(), HttpStatus.OK);
	}

}