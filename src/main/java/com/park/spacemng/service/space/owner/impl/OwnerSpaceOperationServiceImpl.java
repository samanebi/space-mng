package com.park.spacemng.service.space.owner.impl;

import java.util.Objects;

import com.park.spacemng.api.web.space.owner.mapper.OwnerSpaceOperationResourceMapper;
import com.park.spacemng.exception.ParameterValidationException;
import com.park.spacemng.service.space.owner.OwnerSpaceOperationService;
import com.park.spacemng.service.space.owner.model.OwnerSpaceGenerationModel;
import com.park.spacemng.service.space.owner.model.OwnerSpaceRetrievalModel;
import com.park.spacemng.service.space.owner.model.OwnerSpaceRetrievalResult;
import com.park.spacemng.service.space.owner.model.OwnerSpaceUpdateModel;
import com.park.spacemng.service.space.owner.model.SpaceRequestsResolutionModel;
import com.park.spacemng.service.space.owner.model.SpaceRequestsRetrievalModel;
import com.park.spacemng.service.space.owner.model.SpaceRequestsRetrievalResult;
import com.park.spacemng.service.space.space.SpaceOperationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OwnerSpaceOperationServiceImpl implements OwnerSpaceOperationService {

	private final OwnerSpaceOperationResourceMapper mapper;

	private final SpaceOperationService spaceOperationService;

	@Override
	public void generateSpaces(OwnerSpaceGenerationModel model) throws ParameterValidationException {
		validateGeneraSpacesParameter(model);
		spaceOperationService.generate(mapper.toSpaceGenerationModel(model));
	}

	private void validateGeneraSpacesParameter(OwnerSpaceGenerationModel model) throws ParameterValidationException {
		if (Objects.isNull(model.getAddress()) || Objects.equals(model.getAddress(), "")) {
			throw new ParameterValidationException("address is not valid : {}" + model.getAddress());
		}
		if (Objects.isNull(model.getBatchId()) || Objects.equals(model.getBatchId(), "")) {
			throw new ParameterValidationException("batch id is not valid : {}" + model.getAddress());
		}
		if (Objects.isNull(model.getDescription()) || Objects.equals(model.getDescription(), "")) {
			throw new ParameterValidationException("description is not valid : {}" + model.getAddress());
		}
		if (Objects.isNull(model.getLocation())) {
			throw new ParameterValidationException("location is null");
		}
		if (Objects.isNull(model.getTitle()) || Objects.equals(model.getTitle(), "")) {
			throw new ParameterValidationException("title is not valid ");
		}
		if (Objects.equals(model.getLocation().getX(), 0.0)) {
			throw new ParameterValidationException("location x is zero in request : " + model);
		}
		if (Objects.equals(model.getLocation().getY(), 0.0)) {
			throw new ParameterValidationException("location y is zero in request : " + model);
		}
	}

	@Override
	public void updateSpace(OwnerSpaceUpdateModel model) {
		spaceOperationService.updateSpace(mapper.toSpaceUpdateModel(model));
	}

	@Override
	public SpaceRequestsRetrievalResult getSpaceRequests(SpaceRequestsRetrievalModel model) {
		return null;
	}

	@Override
	public void resolveSpaceRequests(SpaceRequestsResolutionModel model) {
	}

	@Override
	public OwnerSpaceRetrievalResult querySpaces(OwnerSpaceRetrievalModel model) {
		return null;
	}

}