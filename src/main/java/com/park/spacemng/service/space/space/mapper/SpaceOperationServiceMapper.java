package com.park.spacemng.service.space.space.mapper;

import java.util.List;

import com.park.spacemng.model.space.space.Space;
import com.park.spacemng.model.user.owner.Owner;
import com.park.spacemng.service.space.space.model.SpaceGenerationModel;
import com.park.spacemng.service.space.space.model.SpaceInfo;
import com.park.spacemng.service.space.space.model.SpaceUpdateModel;
import com.park.spacemng.service.user.owner.model.OwnerInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SpaceOperationServiceMapper {

	SpaceInfo toSpaceInfo(Space space);

	List<SpaceInfo> toSpaceInfos(List<Space> spaces);

	Space toSpace(SpaceGenerationModel model);

	Owner toOwner(OwnerInfo ownerInfo);

	SpaceGenerationModel toSpaceGenerationModel(SpaceUpdateModel model);

}