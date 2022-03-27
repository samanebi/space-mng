package com.park.spacemng.service.space.owner.model;

import java.util.List;

import com.park.spacemng.model.constants.RequestResolution;
import lombok.Data;

@Data
public class SpaceRequestsResolutionModel {

	private List<SpaceBookingModel> requests;

	private RequestResolution resolution;

}