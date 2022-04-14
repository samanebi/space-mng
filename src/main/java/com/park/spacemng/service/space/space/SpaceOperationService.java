package com.park.spacemng.service.space.space;

import java.util.List;

import com.park.spacemng.model.space.Space;
import com.park.spacemng.service.space.space.model.SpaceGenerationModel;
import com.park.spacemng.service.space.space.model.SpaceInfo;
import com.park.spacemng.service.space.space.model.SpaceUpdateModel;

public interface SpaceOperationService {

	List<SpaceInfo> retrieveSpace(String batchId, Space.Status status);

	void takeUnderProcess(String spaceId, String batchId);

	void generate(SpaceGenerationModel model);

	void updateSpace(SpaceUpdateModel model);

}