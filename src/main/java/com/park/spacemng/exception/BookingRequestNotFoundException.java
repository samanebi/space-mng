package com.park.spacemng.exception;

import com.park.spacemng.model.constants.ProcessStatus;

public class BookingRequestNotFoundException extends GeneralException {

	public BookingRequestNotFoundException(String message) {
		super(ProcessStatus.BOOKING_REQUEST_NOT_FOUND, message);
	}

}