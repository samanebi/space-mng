package com.park.spacemng.api.web.user.mapper;

import com.park.spacemng.model.constants.UserStatus;
import com.park.spacemng.model.constants.UserStatusDto;
import com.park.spacemng.model.constants.UserTypeDto;
import com.park.spacemng.model.dto.UserDetailsDto;
import com.park.spacemng.model.request.DriverRegistrationRequest;
import com.park.spacemng.model.request.OwnerRegistrationRequest;
import com.park.spacemng.model.response.LoginResponse;
import com.park.spacemng.model.user.User;
import com.park.spacemng.model.user.constants.UserType;
import com.park.spacemng.service.user.driver.model.DriverRegistrationModel;
import com.park.spacemng.service.user.owner.model.LoginResult;
import com.park.spacemng.service.user.owner.model.OwnerRegistrationModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ValueMapping;

@Mapper(componentModel = "spring")
public interface UserOperationResourceMapper {

	DriverRegistrationModel toDriverRegistrationModel(DriverRegistrationRequest request);

	OwnerRegistrationModel toOwnerRegistrationModel(OwnerRegistrationRequest request);

	UserType toUserType(UserTypeDto userType);

	@Mapping(target = "user.userStatus", source = "userStatus")
	@Mapping(target = "user.userId", source = "user.id")
	@Mapping(target = "user.name", source = "user.name")
	@Mapping(target = "user.surname", source = "user.surname")
	LoginResponse toLoginResponse(LoginResult loginResult);

	@ValueMapping(target = "OFFLINE", source = "OFFLINE")
	@ValueMapping(target = "ONLINE", source = "ONLINE")
	UserStatusDto toUserStatusDto(UserStatus userStatus);

}