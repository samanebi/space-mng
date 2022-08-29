package com.park.spacemng.service.booking.model;

import com.park.spacemng.model.constants.CarSize;
import lombok.Data;

@Data
public class BookingInitiationModel {

	private String batchId;

	private String spaceId;

	private String carId;

	private String ownerId;

	private String driverId;

	private long price;

	private CarSize carSize;

}