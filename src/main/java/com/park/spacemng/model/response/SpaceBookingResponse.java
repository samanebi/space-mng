package com.park.spacemng.model.response;

import com.park.spacemng.model.constants.ProcessStatus;
import com.park.spacemng.model.dto.SpaceDetailsDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class SpaceBookingResponse extends GeneralResponse {

	private SpaceDetailsDto space;

	private String trackingCode;

	public SpaceBookingResponse() {
		super(ProcessStatus.SUCCESS);
	}

	public SpaceBookingResponse(SpaceDetailsDto space) {
		super(ProcessStatus.SUCCESS);
		this.space = space;
	}

}