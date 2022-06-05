package com.park.spacemng.service.booking;

import java.util.List;

import com.park.spacemng.exception.GeneralException;
import com.park.spacemng.service.booking.model.BookingInitiationModel;
import com.park.spacemng.service.booking.model.BookingRequestDetails;
import com.park.spacemng.service.booking.model.BookingRequestsRetrievalResult;

public interface BookingOperationService {

	String initiateBookingRequest(BookingInitiationModel model) throws GeneralException;

	BookingRequestsRetrievalResult retrieveRequests(String batchId);

	void resolve(List<BookingRequestDetails> requestDetails);

	void pay(String trackingCode);

}