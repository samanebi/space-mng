package com.park.spacemng.service.space.owner.model;

import com.park.spacemng.model.constants.RequestResolution;
import lombok.Data;

@Data
public class SpaceBookingModel {

	private String trackingCode;

	private RequestResolution resolution;

}