package com.park.spacemng.service.booking.impl;

import com.park.spacemng.exception.BookingRequestNotFoundException;
import com.park.spacemng.exception.GeneralException;
import com.park.spacemng.model.booking.BookingRequest;
import com.park.spacemng.model.booking.BookingRequest.Status;
import com.park.spacemng.model.booking.dao.BookingRequestDao;
import com.park.spacemng.model.constants.RequestResolution;
import com.park.spacemng.model.space.space.Space;
import com.park.spacemng.model.user.driver.Driver;
import com.park.spacemng.model.user.owner.Owner;
import com.park.spacemng.service.booking.BookingOperationService;
import com.park.spacemng.service.booking.mapper.BookingOperationServiceMapper;
import com.park.spacemng.service.booking.model.BookingInitiationModel;
import com.park.spacemng.service.booking.model.BookingRequestDetails;
import com.park.spacemng.service.booking.model.BookingRequestsRetrievalResult;
import com.park.spacemng.service.space.space.SpaceOperationService;
import com.park.spacemng.service.trackincode.TrackingCodeOperationService;
import com.park.spacemng.service.user.user.UserOperationService;
import com.park.spacemng.util.ExceptionGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingOperationServiceImpl implements BookingOperationService {

	private final UserOperationService<Driver> driverOperationService;

	private final UserOperationService<Owner> ownerOperationService;

	private final BookingOperationServiceMapper mapper;

	private final TrackingCodeOperationService trackingCodeOperationService;

	private final BookingRequestDao dao;

	private final SpaceOperationService spaceOperationService;

	@Override
	public String initiateBookingRequest(BookingInitiationModel model) throws GeneralException {
		Driver driverInfo = driverOperationService.retrieveUser(model.getDriverId());
		Owner ownerInfo = ownerOperationService.retrieveUser(model.getOwnerId());
		long currentDate = new Date().getTime();
		BookingRequest request = new BookingRequest();
		request.setBatchId(model.getBatchId());
		request.setPrice(model.getPrice());
		request.setCarSize(model.getCarSize());
		request.setCreationDate(currentDate);
		request.setExerciseDate(currentDate);
		request.setStateChangedDate(currentDate);
		request.setTrackingCode(trackingCodeOperationService.generate());
		request.setSpaceId(model.getSpaceId());
		request.setCarId(model.getCarId());
		request.setDriver(driverInfo);
		request.setOwner(ownerInfo);
		request.setStatus(Status.INITIATED);
		return dao.save(request).getTrackingCode();
	}

	@Override
	public BookingRequestsRetrievalResult retrieveRequests(String batchId) {
		List<BookingRequest> requests = dao.findAllByBatchId(batchId);
		return mapper.toBookingRequestsRetrievalResult(requests);
	}

	@Override
	public Map<String, Integer> resolve(List<BookingRequestDetails> bookingRequestDetails) {
		Map<String, RequestResolution> requests = mapper.toToBookingRequestList(bookingRequestDetails);
		List<BookingRequest> bookingRequests = dao.findAllByTrackingCodes(getTrackingCodes(requests)).stream()
				.peek(ExceptionGenerator.runtimeExceptionWrapper(request -> {
					Status status = mapper.toStatus(requests.get(request.getTrackingCode()));
					request.setStatus(status);
					spaceOperationService.takeSpace(request.getSpaceId());
				}, GeneralException.class)).toList();
		dao.saveAll(bookingRequests);
		return constructRestMap(bookingRequestDetails);
	}

	private Map<String, Integer> constructRestMap(List<BookingRequestDetails> bookingRequestDetails) {
		Map<String, Integer> rest = new HashMap<>();
		for (BookingRequestDetails details : bookingRequestDetails){
			if (!rest.containsKey(details.getBatchId())){
				rest.put(details.getBatchId(), spaceOperationService.retrieveSpace(details.getBatchId(),
						Space.Status.FREE).size());
			}
		}
		return rest;
	}

	@Override
	public void pay(String trackingCode) {
		BookingRequest request = dao.findByTrackingCode(trackingCode).orElseThrow(() ->
				new BookingRequestNotFoundException("request with tracking code " + trackingCode
						+ " does not exist for payment."));
		request.setStatus(Status.PAYED);
		dao.save(request);
	}

	@Override
	public void evacuate(String trackingCode) {
		BookingRequest request = dao.findByTrackingCode(trackingCode)
				.orElseThrow(() -> new BookingRequestNotFoundException("request with tracking code " + trackingCode
						+ " does not exist for evacuation."));
		if (request.getStatus().equals(Status.PAYED)) {
			request.setStatus(Status.CONFIRMED);
			spaceOperationService.freeSpace(request.getSpaceId());
		} else {
			throw new IllegalArgumentException("request cant be evacuated before payment.");
		}
		dao.save(request);
	}

	@Override
	public BookingRequest getRequest(String trackingCode) {
		return dao.findByTrackingCode(trackingCode).orElseThrow(() ->
				new BookingRequestNotFoundException("request with tracking code " + trackingCode
						+ " does not exist ."));
	}

	private List<String> getTrackingCodes(Map<String, RequestResolution> requests) {
		return requests.keySet().stream().toList();
	}

}