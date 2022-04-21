package com.park.spacemng.service.booking;

import java.util.List;

import com.park.spacemng.exception.DriverNotFoundException;
import com.park.spacemng.exception.OwnerNotFoundException;
import com.park.spacemng.model.constants.RequestResolution;
import com.park.spacemng.service.booking.model.BookingInitiationModel;
import com.park.spacemng.service.booking.model.BookingRequestDetails;
import com.park.spacemng.service.booking.model.BookingRequestsRetrievalResult;

public interface BookingOperationService {

	String initiateBookingRequest(BookingInitiationModel model) throws OwnerNotFoundException, DriverNotFoundException;

	BookingRequestsRetrievalResult retrieveRequests(String batchId);

	void resolve(List<BookingRequestDetails> requestDetails, RequestResolution resolution);

}