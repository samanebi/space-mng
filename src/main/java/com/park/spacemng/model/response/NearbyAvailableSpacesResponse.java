package com.park.spacemng.model.response;

import com.park.spacemng.model.constants.ProcessStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NearbyAvailableSpacesResponse extends GeneralResponse {

	public NearbyAvailableSpacesResponse() {
		super(ProcessStatus.SUCCESS);
	}

}