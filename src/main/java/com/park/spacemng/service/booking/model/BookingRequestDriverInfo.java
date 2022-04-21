package com.park.spacemng.service.booking.model;

import com.park.spacemng.model.user.User;
import lombok.Data;

@Data
public class BookingRequestDriverInfo {

	private String driverId;

	private String name;

	private String surname;

	private User.Status status;

}