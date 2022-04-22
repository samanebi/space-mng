package com.park.spacemng.model.request;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class SpaceResolutionRequest {

	@NotEmpty
	private List<SpaceBookingRequest> requests;

}