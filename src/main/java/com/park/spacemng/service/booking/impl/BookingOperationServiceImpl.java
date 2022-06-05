package com.park.spacemng.service.booking.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.park.spacemng.exception.BookingRequestNotFoundException;
import com.park.spacemng.exception.GeneralException;
import com.park.spacemng.model.booking.BookingRequest;
import com.park.spacemng.model.booking.BookingRequest.Status;
import com.park.spacemng.model.booking.dao.BookingRequestDao;
import com.park.spacemng.model.constants.RequestResolution;
import com.park.spacemng.service.booking.BookingOperationService;
import com.park.spacemng.service.booking.mapper.BookingOperationServiceMapper;
import com.park.spacemng.service.booking.model.BookingInitiationModel;
import com.park.spacemng.service.booking.model.BookingRequestDetails;
import com.park.spacemng.service.booking.model.BookingRequestsRetrievalResult;
import com.park.spacemng.service.space.space.SpaceOperationService;
import com.park.spacemng.service.trackincode.TrackingCodeOperationService;
import com.park.spacemng.service.user.driver.DriverOperationService;
import com.park.spacemng.service.user.driver.model.DriverInfo;
import com.park.spacemng.service.user.owner.OwnerOperationService;
import com.park.spacemng.service.user.owner.model.OwnerInfo;
import com.park.spacemng.util.ExceptionGenerator;
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

	private final SpaceOperationService spaceOperationService;

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
		request.setSpaceId(model.getSpaceId());
		request.setCarId(model.getCarId());
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
		Map<String, RequestResolution> requests = mapper.toToBookingRequestList(bookingRequestDetails);
		List<BookingRequest> bookingRequests = dao.findAllByTrackingCodes(getTrackingCodes(requests)).stream()
				.peek(ExceptionGenerator.runtimeExceptionWrapper(request -> {
					request.setStatus(mapper.toStatus(requests.get(request.getTrackingCode())));
					spaceOperationService.takeSpace(request.getSpaceId());
				}, GeneralException.class)).toList();
		dao.saveAll(bookingRequests);
	}

	@Override
	public void pay(String trackingCode) {
		BookingRequest request = dao.findByTrackingCode(trackingCode).orElseThrow(() ->
				new BookingRequestNotFoundException("request with tracking code " + trackingCode
						+ " does not exists."));
		request.setStatus(Status.PAYED);
		dao.save(request);
	}

	private List<String> getTrackingCodes(Map<String, RequestResolution> requests) {
		return requests.keySet().stream().toList();
	}

}