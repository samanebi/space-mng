package com.park.spacemng.service.space.space;

import java.util.List;

import com.park.spacemng.exception.GeneralException;
import com.park.spacemng.exception.SpaceNotFoundException;
import com.park.spacemng.model.space.space.Space;
import com.park.spacemng.model.user.owner.Owner;
import com.park.spacemng.service.space.space.model.SpaceGenerationModel;
import com.park.spacemng.service.space.space.model.SpaceInfo;
import com.park.spacemng.service.space.space.model.SpaceQueryModel;
import com.park.spacemng.service.space.space.model.SpaceUpdateModel;

import org.springframework.data.geo.Point;

public interface SpaceOperationService {

	List<SpaceInfo> retrieveSpace(String batchId, Space.Status status);

	Owner retrieveOwner(String batchId);

	void takeUnderProcess(String spaceId) throws SpaceNotFoundException;

	void takeSpace(String spaceId) throws SpaceNotFoundException;

	void freeSpace(String spaceId) throws SpaceNotFoundException;

	void generate(SpaceGenerationModel model) throws GeneralException;

	void updateSpace(SpaceUpdateModel model) throws GeneralException;

	List<Space> querySpaces(SpaceQueryModel model);

	List<Space> findByPoint(Point point);

	void delete(String batchId);
}