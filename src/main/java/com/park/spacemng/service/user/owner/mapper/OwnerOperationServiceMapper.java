package com.park.spacemng.service.user.owner.mapper;

import com.park.spacemng.model.dto.SpaceDetailsDto;
import com.park.spacemng.model.space.space.Space;
import com.park.spacemng.model.user.owner.Owner;
import com.park.spacemng.service.user.owner.model.OwnerInfo;
import com.park.spacemng.service.user.owner.model.OwnerRegistrationModel;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OwnerOperationServiceMapper {

	@Mapping(target = "ownerId", source = "id")
	OwnerInfo toOwnerInfo(Owner owner);

	Owner toOwner(OwnerRegistrationModel model);

	@Mapping(target = "location.position", source = "space.position")
	SpaceDetailsDto toSpaceDetailsDto(Space space, int capacity);

}