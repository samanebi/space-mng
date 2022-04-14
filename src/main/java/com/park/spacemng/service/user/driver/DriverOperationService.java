package com.park.spacemng.service.user.driver;

import java.util.Optional;

import com.park.spacemng.service.user.driver.model.DriverInfo;
import com.park.spacemng.service.user.driver.model.DriverRetrievalModel;

public interface DriverOperationService {

	// todo :  remember to implement
	Optional<DriverInfo> retriveDriver(DriverRetrievalModel model);

}