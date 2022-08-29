package com.park.spacemng.api.web.space.driver;

import com.park.spacemng.api.web.space.driver.mapper.DriverSpaceOperationResourceMapper;
import com.park.spacemng.exception.GeneralException;
import com.park.spacemng.model.booking.BookingRequest;
import com.park.spacemng.model.constants.ProcessStatus;
import com.park.spacemng.model.request.NearbyAvailableSpacesRequest;
import com.park.spacemng.model.request.SpaceBookingRequest;
import com.park.spacemng.model.response.BookingRequestRetrievalResponse;
import com.park.spacemng.model.response.GeneralResponse;
import com.park.spacemng.model.response.NearbyAvailableSpacesResponse;
import com.park.spacemng.model.response.SpaceBookingResponse;
import com.park.spacemng.service.space.driver.DriverSpaceOperationService;
import com.park.spacemng.service.space.driver.model.DriverSpaceBookingResult;
import com.park.spacemng.service.space.driver.model.NearbyAvailableSpacesRetrievalResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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
	public ResponseEntity<SpaceBookingResponse> bookSpace(@NotNull @RequestBody SpaceBookingRequest request)
			throws GeneralException {
		DriverSpaceBookingResult response = service.bookSpace(mapper.toDriverBookingModel(request));
		return new ResponseEntity<>(mapper.toBookingSpaceResponse(response), HttpStatus.OK);
	}

	@GetMapping(value = "/evacuate/{trackingCode}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GeneralResponse> evacuate(
			@PathVariable String trackingCode) throws GeneralException {
		service.evacuate(trackingCode);
		return new ResponseEntity<>(new GeneralResponse(ProcessStatus.SUCCESS), HttpStatus.OK);
	}

	@GetMapping(value = "/{trackingCode}")
	public ResponseEntity<BookingRequestRetrievalResponse> getRequest(
			@PathVariable String trackingCode) throws GeneralException {
		BookingRequest request = service.getRequest(trackingCode);
		return new ResponseEntity<>(mapper.toBookingRequestRetrievalResponse(request), HttpStatus.OK);
	}

}