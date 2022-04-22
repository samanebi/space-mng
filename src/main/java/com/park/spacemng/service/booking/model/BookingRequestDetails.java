package com.park.spacemng.service.booking.model;

import com.park.spacemng.model.constants.RequestResolution;
import lombok.Data;

@Data
public class BookingRequestDetails {

	private String trackingCode;

	private String batchId;

	private BookingRequestOwnerInfo owner;

	private BookingRequestDriverInfo driver;

	private long amount;

	private RequestResolution resolution;

}