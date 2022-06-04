package com.park.spacemng.service.booking.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class BookingRequestsRetrievalResult {

	private List<BookingRequestDetails> requests;

}