package com.park.spacemng.service.booking.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.park.spacemng.exception.GeneralException;
import com.park.spacemng.model.booking.BookingRequest;
import com.park.spacemng.model.booking.BookingRequest.Status;
import com.park.spacemng.model.booking.dao.BookingRequestDao;
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

	private final BookingRequestDao dao;

	@Override
	public String initiateBookingRequest(BookingInitiationModel model) throws GeneralException {
		DriverInfo driverInfo = driverOperationService.retrieveDriver(model.getDriverId());
		OwnerInfo ownerInfo = ownerOperationService.retrieveOwner(model.getOwnerId());
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
		return dao.save(request).getTrackingCode();
	}

	@Override
	public BookingRequestsRetrievalResult retrieveRequests(String batchId) {
		List<BookingRequest> requests = dao.findAllByBatchId(batchId);
		return mapper.toBookingRequestsRetrievalResult(requests);
	}

	@Override
	public void resolve(List<BookingRequestDetails> bookingRequestDetails) {
		List<BookingRequest> requests = mapper.toToBookingRequestList(bookingRequestDetails);
		requests = dao.findAllByTrackingCodes(getTrackingCodes(requests));
		dao.saveAll(requests);
	}

	private List<String> getTrackingCodes(List<BookingRequest> requests) {
		return requests.stream().map(BookingRequest::getTrackingCode).collect(Collectors.toList());
	}

}