package com.park.spacemng.service.user.driver;

import com.park.spacemng.exception.GeneralException;
import com.park.spacemng.service.user.driver.model.DriverInfo;

public interface DriverOperationService {

	DriverInfo retrieveDriver(String driverId) throws GeneralException;

}