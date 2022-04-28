package com.park.spacemng.service.user.driver.model;

import com.park.spacemng.model.user.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class DriverInfo {

	private String driverId;

	private String name;

	private String surname;

	private User.Status status;

}