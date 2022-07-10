package com.park.spacemng.service.user.driver.mapper;

import com.park.spacemng.model.user.driver.Driver;
import com.park.spacemng.service.user.driver.model.DriverInfo;
import com.park.spacemng.service.user.driver.model.DriverRegistrationModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DriverOperationServiceMapper {

	@Mapping(target = "driverId", source = "id")
	DriverInfo toDriverInfo(Driver driver);

	Driver toDriver(DriverRegistrationModel model);

}