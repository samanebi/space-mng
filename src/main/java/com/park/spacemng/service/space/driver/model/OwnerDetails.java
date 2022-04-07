package com.park.spacemng.service.space.driver.model;

import com.park.spacemng.model.user.User;
import lombok.Data;

@Data
public class OwnerDetails {

	private String ownerId;

	private String name;

	private String surname;

	private User.Status status;

}