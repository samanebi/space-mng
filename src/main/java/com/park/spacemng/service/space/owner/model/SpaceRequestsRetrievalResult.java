package com.park.spacemng.service.space.owner.model;

import java.util.List;

import com.park.spacemng.service.booking.model.BookingRequestDetails;
import lombok.Data;

@Data
public class SpaceRequestsRetrievalResult {

	private List<BookingRequestDetails> requests;

}