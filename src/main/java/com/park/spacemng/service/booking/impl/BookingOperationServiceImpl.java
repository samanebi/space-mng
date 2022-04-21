package com.park.spacemng.service.booking.impl;

import java.util.Date;
import java.util.List;

import com.park.spacemng.exception.DriverNotFoundException;
import com.park.spacemng.exception.OwnerNotFoundException;
import com.park.spacemng.model.booking.BookingRequest;
import com.park.spacemng.model.booking.BookingRequest.Status;
import com.park.spacemng.model.constants.RequestResolution;
import com.park.spacemng.service.booking.BookingOperationService;
import com.park.spacemng.service.booking.mapper.BookingOperationServiceMapper;
import com.park.spacemng.service.booking.model.BookingInitiationModel;
import com.park.spacemng.service.booking.model.BookingRequestDetails;
import com.park.spacemng.service.booking.model.BookingRequestsRetrievalResult;
import com.park.spacemng.service.trackincode.TrackingCodeOperationService;
import com.park.spacemng.service.user.driver.DriverOperationService;
import com.park.spacemng.service.user.driver.model.DriverInfo;
import com.park.spacemng.service.user.owner.OwnerOperationService;
import com.park.spacemng.service.user.owner.model.OwnerInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingOperationServiceImpl implements BookingOperationService {

	private final DriverOperationService driverOperationService;

	private final OwnerOperationService ownerOperationService;

	private final BookingOperationServiceMapper mapper;

	private final TrackingCodeOperationService trackingCodeOperationService;

	@Override
	public String initiateBookingRequest(BookingInitiationModel model) throws OwnerNotFoundException, DriverNotFoundException {
		DriverInfo driverInfo = driverOperationService.retrieveDriver(model.getDriverId())
				.orElseThrow(() -> new DriverNotFoundException("driver not found : " + model.getDriverId()));
		OwnerInfo ownerInfo = ownerOperationService.retrieveOwner(model.getOwnerId())
				.orElseThrow(() -> new OwnerNotFoundException("owner not found : " + model.getOwnerId()));
		long currentDate = new Date().getTime();
		BookingRequest request = new BookingRequest();
		request.setBatchId(model.getBatchId());
		request.setAmount(model.getAmount());
		request.setCreationDate(currentDate);
		request.setExerciseDate(currentDate);
		request.setStateChangedDate(currentDate);
		request.setTrackingCode(trackingCodeOperationService.generate());
		request.setDriver(mapper.toDriver(driverInfo));
		request.setOwner(mapper.toOwner(ownerInfo));
		request.setStatus(Status.INITIATED);
		// TODO : DAO save
		return request.getTrackingCode();
	}

	@Override
	public BookingRequestsRetrievalResult retrieveRequests(String batchId) {
		// TODO :  retrieve from DAO
		List<BookingRequest> requests = null;
		return mapper.toBookingRequestsRetrievalResult(requests);
	}

	@Override
	public void resolve(List<BookingRequestDetails> bookingRequestDetails, RequestResolution resolution) {
		List<BookingRequest> requests = mapper.toToBookingRequestList(bookingRequestDetails);
		// TODO : save with DAO
	}

}