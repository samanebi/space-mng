package com.park.spacemng.service.booking;

import com.park.spacemng.model.constants.RequestResolution;
import com.park.spacemng.service.booking.model.BookingInitiationModel;
import com.park.spacemng.service.booking.model.BookingInitiationResult;
import com.park.spacemng.service.booking.model.BookingRequestsRetrievalResult;

public interface BookingOperationService {

	BookingInitiationResult initiateBookingRequest(BookingInitiationModel model);

	BookingRequestsRetrievalResult retrieveRequests(String batchId);

	void resolve(String trackingCode, RequestResolution resolution);

}