package com.park.spacemng.service.user.driver;

import java.util.Optional;

import com.park.spacemng.service.user.driver.model.DriverInfo;

public interface DriverOperationService {

	// todo :  remember to implement
	Optional<DriverInfo> retrieveDriver(String driverId);

}