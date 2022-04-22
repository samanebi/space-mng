package com.park.spacemng.service.user.owner.mapper;

import com.park.spacemng.model.user.owner.Owner;
import com.park.spacemng.service.user.owner.model.OwnerInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OwnerOperationServiceMapper {

	OwnerInfo toOwnerInfo(Owner owner);

}