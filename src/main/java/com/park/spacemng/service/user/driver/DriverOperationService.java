package com.park.spacemng.service.user.driver;

import com.park.spacemng.exception.GeneralException;
import com.park.spacemng.service.user.driver.model.DriverInfo;
import com.park.spacemng.service.user.driver.model.DriverRegistrationModel;

public interface DriverOperationService {

	DriverInfo retrieveDriver(String driverId) throws GeneralException;

	void registerDriver(DriverRegistrationModel model);

}