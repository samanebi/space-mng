package com.park.spacemng.service.user.owner.model;

import com.park.spacemng.model.user.User;
import lombok.Data;

@Data
public class OwnerInfo {

	private String ownerId;

	private String name;

	private String surname;

	private User.Status status;

}