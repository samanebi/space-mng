package com.park.spacemng.service.space.owner.impl;

import java.util.List;
import java.util.Map;

import com.park.spacemng.api.web.space.owner.mapper.OwnerSpaceOperationResourceMapper;
import com.park.spacemng.exception.GeneralException;
import com.park.spacemng.exception.ParameterValidationException;
import com.park.spacemng.model.space.space.Space;
import com.park.spacemng.service.booking.BookingOperationService;
import com.park.spacemng.service.booking.model.BookingRequestsRetrievalResult;
import com.park.spacemng.service.space.owner.OwnerSpaceOperationService;
import com.park.spacemng.service.space.owner.model.OwnerSpaceGenerationModel;
import com.park.spacemng.service.space.owner.model.OwnerSpaceRetrievalModel;
import com.park.spacemng.service.space.owner.model.OwnerSpaceRetrievalResult;
import com.park.spacemng.service.space.owner.model.OwnerSpaceUpdateModel;
import com.park.spacemng.service.space.owner.model.SpaceRequestsResolutionModel;
import com.park.spacemng.service.space.owner.model.SpaceRequestsRetrievalResult;
import com.park.spacemng.service.space.space.SpaceOperationService;
import com.park.spacemng.util.ParameterValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;

import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OwnerSpaceOperationServiceImpl implements OwnerSpaceOperationService {

	private final OwnerSpaceOperationResourceMapper mapper;

	private final SpaceOperationService spaceOperationService;

	private final BookingOperationService bookingOperationService;

	private final ParameterValidator parameterValidator;

	@Override
	public void generateSpaces(OwnerSpaceGenerationModel model) throws GeneralException {
		validateGeneraSpacesParameter(model);
		spaceOperationService.generate(mapper.toSpaceGenerationModel(model));
	}

	private void validateGeneraSpacesParameter(OwnerSpaceGenerationModel model) throws GeneralException {
		parameterValidator.requireParameterNotNullOrBlank(model.getAddress());
		parameterValidator.requireParameterNotNullOrBlank(model.getBatchId());
		parameterValidator.requireParameterNotNullOrBlank(model.getOwnerId());
		parameterValidator.requireParameterNotNullOrBlank(model.getDescription());
		parameterValidator.requireParameterNotNull(model.getSpaceLocation());
		parameterValidator.requireParameterNotNullOrBlank(model.getTitle());
		parameterValidator.requireParameterNotEqualTo(model.getSpaceLocation().getPosition().getX(), 0.0);
		parameterValidator.requireParameterNotEqualTo(model.getSpaceLocation().getPosition().getY(), 0.0);
	}

	@Override
	public void updateSpace(OwnerSpaceUpdateModel model) throws GeneralException {
		spaceOperationService.updateSpace(mapper.toSpaceUpdateModel(model));
	}

	@Override
	public SpaceRequestsRetrievalResult getSpaceRequests(String batchId) throws ParameterValidationException {
		if (Strings.isBlank(batchId)) {
			throw new ParameterValidationException("batch id is empty or null.");
		}

		BookingRequestsRetrievalResult bookingRequestsRetrievalResult = bookingOperationService
				.retrieveRequests(batchId);
		return mapper.toSpaceRequestsRetrievalResult(bookingRequestsRetrievalResult);
	}

	@Override
	public Map<String, Integer> resolveSpaceRequests(SpaceRequestsResolutionModel model) throws ParameterValidationException {
		verifyResolutionRequestParameters(model);
		return bookingOperationService.resolve(mapper.toBookingRequestDetailsList(model.getRequests()));
	}

	private void verifyResolutionRequestParameters(SpaceRequestsResolutionModel model)
			throws ParameterValidationException {
		if (model.getRequests().isEmpty()) {
			throw new ParameterValidationException("requests are empty!");
		}
	}

	@Override
	public OwnerSpaceRetrievalResult querySpaces(OwnerSpaceRetrievalModel model) {
		List<Space> spaces = spaceOperationService.querySpaces(mapper.toSpaceQueryModel(model));
		return new OwnerSpaceRetrievalResult(spaces);
	}

	@Override
	public OwnerSpaceRetrievalResult findSpaces(Point point) {
		OwnerSpaceRetrievalResult result = new OwnerSpaceRetrievalResult();
		result.setSpaces(spaceOperationService.findByPoint(point));
		return result;
	}

}