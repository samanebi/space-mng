package com.park.spacemng.service.booking.model;

import java.util.List;

import lombok.Data;

@Data
public class BookingRequestsRetrievalResult {

	private List<BookingRequestDetails> requests;

}