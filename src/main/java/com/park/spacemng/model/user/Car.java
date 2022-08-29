package com.park.spacemng.model.user;

import com.park.spacemng.model.constants.CarSize;
import lombok.Data;

@Data
public class Car {

	private String carId;

	private String driverId;

	private String name;

	private String brand;

	private Plate plate;

	private CarSize type;

}