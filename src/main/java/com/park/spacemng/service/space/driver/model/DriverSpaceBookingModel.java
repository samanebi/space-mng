package com.park.spacemng.service.space.driver.model;

import com.park.spacemng.model.constants.CarSize;
import lombok.Data;

@Data
public class DriverSpaceBookingModel {

	private String batchId;

	private String driverId;

	private CarSize carSize;

}