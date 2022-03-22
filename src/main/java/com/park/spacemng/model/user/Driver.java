package com.park.spacemng.model.user;

import java.util.List;

import lombok.Data;

@Data
public class Driver extends User {

	private String driverId;

	private List<Car> car;

}