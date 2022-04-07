package com.park.spacemng.service.user.driver.model;

import com.park.spacemng.model.user.User;
import lombok.Data;

@Data
public class DriverInfo {

	private String driverId;

	private String name;

	private String surname;

	private User.Status status;

}