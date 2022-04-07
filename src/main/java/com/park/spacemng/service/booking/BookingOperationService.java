package com.park.spacemng.service.booking;

import com.park.spacemng.service.booking.model.BookingInitiationModel;
import com.park.spacemng.service.booking.model.BookingInitiationResult;
import com.park.spacemng.service.booking.model.BookingRequestsRetrievalModel;
import com.park.spacemng.service.booking.model.BookingRequestsRetrievalResult;

public interface BookingOperationService {

	BookingInitiationResult initiateBookingRequest(BookingInitiationModel model);

	BookingRequestsRetrievalResult retrieveRequests(BookingRequestsRetrievalModel model);

}