package com.park.spacemng.service.user.owner.mapper;

import com.park.spacemng.model.user.owner.Owner;
import com.park.spacemng.service.user.owner.model.OwnerInfo;
import com.park.spacemng.service.user.owner.model.OwnerRegistrationModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OwnerOperationServiceMapper {

	@Mapping(target = "ownerId", source = "id")
	OwnerInfo toOwnerInfo(Owner owner);

	Owner toOwner(OwnerRegistrationModel model);

}