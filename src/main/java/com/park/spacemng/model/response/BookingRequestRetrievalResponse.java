package com.park.spacemng.model.response;

import com.park.spacemng.model.dto.BookingRequestDetailsDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class BookingRequestRetrievalResponse extends GeneralResponse {

	private BookingRequestDetailsDto details;

}