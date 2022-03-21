package com.park.spacemng.model.response;

import java.util.List;

import com.park.spacemng.model.constants.ProcessStatus;
import com.park.spacemng.model.dto.SpaceDetailsDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NearbyAvailableSpacesResponse extends GeneralResponse {

	private final List<SpaceDetailsDto> spaces;

	public NearbyAvailableSpacesResponse(List<SpaceDetailsDto> spaces) {
		super(ProcessStatus.SUCCESS);
		this.spaces = spaces;
	}

}