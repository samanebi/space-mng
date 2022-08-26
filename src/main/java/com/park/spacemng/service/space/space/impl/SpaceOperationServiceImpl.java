package com.park.spacemng.service.space.space.impl;

import com.park.spacemng.config.LocationSelectionProperties;
import com.park.spacemng.exception.GeneralException;
import com.park.spacemng.exception.SpaceNotFoundException;
import com.park.spacemng.model.space.space.Space;
import com.park.spacemng.model.space.space.Space.Status;
import com.park.spacemng.model.space.space.dao.SpaceDao;
import com.park.spacemng.model.user.owner.Owner;
import com.park.spacemng.service.space.space.SpaceOperationService;
import com.park.spacemng.service.space.space.mapper.SpaceOperationServiceMapper;
import com.park.spacemng.service.space.space.model.SpaceGenerationModel;
import com.park.spacemng.service.space.space.model.SpaceInfo;
import com.park.spacemng.service.space.space.model.SpaceQueryModel;
import com.park.spacemng.service.space.space.model.SpaceUpdateModel;
import com.park.spacemng.service.user.user.UserOperationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SpaceOperationServiceImpl implements SpaceOperationService {

	private static final Random random = new Random();

	private final SpaceDao dao;

	private final SpaceOperationServiceMapper mapper;

	private final UserOperationService<Owner> ownerOperationService;

	private final LocationSelectionProperties properties;

	@Override
	public List<SpaceInfo> retrieveSpace(String batchId, Status status) {
		List<Space> spaces = dao.findAllByBatchIdAndStatus(batchId, status);
		return mapper.toSpaceInfos(spaces);
	}

	@Override
	public void takeUnderProcess(String spaceId) throws SpaceNotFoundException {
		Space space = getSpace(spaceId);
		space.setStatus(Status.PROCESSING);
		dao.save(space);
	}

	@Override
	public void takeSpace(String spaceId) throws SpaceNotFoundException {
		Space space = getSpace(spaceId);
		space.setStatus(Status.TAKEN);
		dao.save(space);
	}

	@Override
	public void freeSpace(String spaceId) throws SpaceNotFoundException {
		Space space = getSpace(spaceId);
		space.setStatus(Status.FREE);
		dao.save(space);
	}

	@Override
	public void generate(SpaceGenerationModel model) throws GeneralException {
		List<Space> spaces = new ArrayList<>();
		Owner owner = ownerOperationService.retrieveUser(model.getOwnerId());
		for (int counter = 0; counter < model.getCapacity(); counter++) {
			Space space = mapper.toSpace(model);
			space.setOwner(owner);
			space.setStatus(Status.FREE);
			spaces.add(space);
		}
		dao.saveAll(spaces);
	}

	@Override
	public void updateSpace(SpaceUpdateModel model) throws GeneralException {
		List<Space> spaces = updateSpaceInformation(model);
		if (spaces.size() > model.getCapacity()) {
			List<Space> freeSpaceList = dao.findAllByBatchIdAndStatus(model.getBatchId(), Status.FREE);
			dao.deleteAll(getRandomSpaces(model, spaces, freeSpaceList));
		} else if (spaces.size() < model.getCapacity()) {
			SpaceGenerationModel spaceGenerationModel = mapper.toSpaceGenerationModel(model);
			spaceGenerationModel.setCapacity(model.getCapacity() - spaces.size());
			generate(spaceGenerationModel);
		}
	}

	@Override
	public List<Space> querySpaces(SpaceQueryModel model) {
		return dao.findAllByStateNameAndDistrictAndTown(model.getStates(),
				model.getDistricts(), model.getTowns());
	}

	@Override
	public List<Space> findByPoint(Point point) {
		List<Space> spaces = dao.findAll();
		spaces.forEach(space -> {
			if (!(Math.abs(space.getPosition().getX() - point.getX()) <= properties.getDistance() &&
					Math.abs(space.getPosition().getY() - point.getY()) <= properties.getDistance())) {
				spaces.remove(space);
			}
		});
		return spaces;
	}

	private List<Space> updateSpaceInformation(SpaceUpdateModel model) {
		List<Space> spaces = dao.findAllByBatchId(model.getBatchId()).stream().peek(space -> {
			space.setAddress(Objects.isNull(model.getAddress())? space.getAddress(): model.getAddress());
			space.setDescription(Objects.isNull(model.getDescription())? space.getDescription():
					model.getDescription());
			Point position = (Objects.isNull(model.getSpaceLocation()) ||
					Objects.isNull(model.getSpaceLocation().getPosition()))
					? space.getPosition() : model.getSpaceLocation().getPosition();
			space.setPosition(position);
			space.setTitle(Objects.isNull(model.getTitle()) ? space.getTitle() : model.getTitle());
			space.setPrice(model.getPrice() == 0L ? space.getPrice() : model.getPrice());
		}).collect(Collectors.toList());
		return dao.saveAll(spaces);
	}

	private List<Space> getRandomSpaces(SpaceUpdateModel model, List<Space> spaces, List<Space> freeSpaceList) {
		List<Space> removeList = new ArrayList<>();
		for (int counter = 0; counter < spaces.size() - model.getCapacity(); counter++) {
			removeList.add(freeSpaceList.get(random.nextInt(spaces.size())));
		}
		return removeList;
	}

	private Space getSpace(String spaceId) throws SpaceNotFoundException {
		return dao.findById(spaceId).orElseThrow(() ->
				new SpaceNotFoundException("space not found : " + spaceId));
	}

}