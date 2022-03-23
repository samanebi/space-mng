package com.park.spacemng.model.request;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.park.spacemng.model.constants.RequestResolution;
import lombok.Data;

@Data
public class SpaceResolutionRequest {

	@NotEmpty
	private List<SpaceAllocationRequest> requests;

	@NotNull
	private RequestResolution resolution;

}