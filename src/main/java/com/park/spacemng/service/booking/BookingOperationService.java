package com.park.spacemng.service.booking;

import java.util.List;
import java.util.Map;

import com.park.spacemng.exception.GeneralException;
import com.park.spacemng.model.booking.BookingRequest;
import com.park.spacemng.service.booking.model.BookingInitiationModel;
import com.park.spacemng.service.booking.model.BookingRequestDetails;
import com.park.spacemng.service.booking.model.BookingRequestsRetrievalResult;

public interface BookingOperationService {

	String initiateBookingRequest(BookingInitiationModel model) throws GeneralException;

	BookingRequestsRetrievalResult retrieveRequests(String batchId);

	Map<String, Integer> resolve(List<BookingRequestDetails> requestDetails);

	void pay(String trackingCode);

	void evacuate(String trackingCode);

	BookingRequest getRequest(String trackingCode);

}