package com.park.spacemng.model.response;

import com.park.spacemng.model.constants.ProcessStatus;
import com.park.spacemng.model.dto.SpaceDetailsDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class BookingSpaceResponse extends GeneralResponse {

	private SpaceDetailsDto space;

	public BookingSpaceResponse() {
		super(ProcessStatus.SUCCESS);
	}

}