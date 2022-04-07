package com.park.spacemng.model.response;

import java.util.List;

import com.park.spacemng.model.constants.ProcessStatus;
import com.park.spacemng.service.booking.model.BookingRequestDetails;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class SpaceRetrievalResponse extends GeneralResponse {

	List<BookingRequestDetails> requests;

	public SpaceRetrievalResponse() {
		super(ProcessStatus.SUCCESS);
	}

}