package com.park.spacemng.service.space.space;

import java.util.List;

import com.park.spacemng.service.space.space.model.SpaceGenerationModel;
import com.park.spacemng.service.space.space.model.SpaceInfo;
import com.park.spacemng.service.space.space.model.SpaceRetrievalModel;
import com.park.spacemng.service.space.space.model.SpaceTakeUnderProcessModel;
import com.park.spacemng.service.space.space.model.SpaceUpdateModel;

public interface SpaceOperationService {

	List<SpaceInfo> retrieveSpace(SpaceRetrievalModel model);

	void takeUnderProcess(SpaceTakeUnderProcessModel model);

	void generate(SpaceGenerationModel model);

	void updateSpace(SpaceUpdateModel model);

}