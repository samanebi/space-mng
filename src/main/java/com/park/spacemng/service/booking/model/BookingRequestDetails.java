package com.park.spacemng.service.booking.model;

import com.park.spacemng.model.constants.CarType;
import com.park.spacemng.model.constants.RequestResolution;
import lombok.Data;

@Data
public class BookingRequestDetails {

	private String trackingCode;

	private String batchId;

	private RequestResolution resolution;

	private long price;

	private CarType carSize;

	private String driverName;

	private String driverSurname;

	private String driverCellNumber;

}