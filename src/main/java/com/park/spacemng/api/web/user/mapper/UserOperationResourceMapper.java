package com.park.spacemng.api.web.user.mapper;

import com.park.spacemng.model.constants.UserTypeDto;
import com.park.spacemng.model.request.DriverRegistrationRequest;
import com.park.spacemng.model.request.OwnerRegistrationRequest;
import com.park.spacemng.model.user.constants.UserType;
import com.park.spacemng.service.user.driver.model.DriverRegistrationModel;
import com.park.spacemng.service.user.owner.model.OwnerRegistrationModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserOperationResourceMapper {

	DriverRegistrationModel toDriverRegistrationModel(DriverRegistrationRequest request);

	OwnerRegistrationModel toOwnerRegistrationModel(OwnerRegistrationRequest request);

	UserType toUserType(UserTypeDto userType);

}