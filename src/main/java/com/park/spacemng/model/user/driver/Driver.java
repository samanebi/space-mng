package com.park.spacemng.model.user.driver;

import com.park.spacemng.model.user.User;
import lombok.Data;

import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "drivers")
public class Driver extends User {

	private String driverId;

}