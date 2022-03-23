package com.park.spacemng.api.web.space;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.park.spacemng.model.request.SpaceGenerationRequest;
import com.park.spacemng.model.request.SpaceResolutionRequest;
import com.park.spacemng.model.request.SpaceUpdateRequest;
import com.park.spacemng.model.response.BookingSpaceResponse;
import com.park.spacemng.util.Constants;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Validated
@RequestMapping("/spaces")
public class OwnerSpaceOperationResource {

	@PostMapping(value = "/generate",
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BookingSpaceResponse> generateSpaces(@NotNull @RequestBody SpaceGenerationRequest request,
			@NotBlank @RequestHeader(Constants.HEADER_USER_ID) String userId) {
		return new ResponseEntity<>(new BookingSpaceResponse(), HttpStatus.OK);
	}

	@PostMapping(value = "/space/update",
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BookingSpaceResponse> updateSpace(@NotNull @RequestBody SpaceUpdateRequest request,
			@NotBlank @RequestHeader(Constants.HEADER_USER_ID) String userId) {
		return new ResponseEntity<>(new BookingSpaceResponse(), HttpStatus.OK);
	}

	@GetMapping(value = "/space/requests/{batchId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BookingSpaceResponse> getSpaceRequests(
			@NotBlank @RequestHeader(Constants.HEADER_USER_ID) String userId,
			@NotBlank @PathVariable String batchId) {
		return new ResponseEntity<>(new BookingSpaceResponse(), HttpStatus.OK);
	}

	@PostMapping(value = "/space/requests/resolve",
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BookingSpaceResponse> resolveSpaceRequests(
			@NotNull @RequestBody SpaceResolutionRequest request,
			@NotBlank @RequestHeader(Constants.HEADER_USER_ID) String userId,
			@NotBlank @PathVariable String batchId) {
		return new ResponseEntity<>(new BookingSpaceResponse(), HttpStatus.OK);
	}

}