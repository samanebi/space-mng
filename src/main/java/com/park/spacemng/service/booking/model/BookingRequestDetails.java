package com.park.spacemng.service.booking.model;

import lombok.Data;

@Data
public class BookingRequestDetails {

	private String trackingCode;

	private String batchId;

	private BookingRequestOwnerInfo owner;

	private BookingRequestDriverInfo driver;

	private long amount;

}