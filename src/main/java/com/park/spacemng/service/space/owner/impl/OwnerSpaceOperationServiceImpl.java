package com.park.spacemng.service.space.owner.impl;

import java.util.Objects;

import com.park.spacemng.api.web.space.owner.mapper.OwnerSpaceOperationResourceMapper;
import com.park.spacemng.exception.ParameterValidationException;
import com.park.spacemng.model.constants.RequestResolution;
import com.park.spacemng.service.booking.BookingOperationService;
import com.park.spacemng.service.booking.model.BookingRequestsRetrievalResult;
import com.park.spacemng.service.space.owner.OwnerSpaceOperationService;
import com.park.spacemng.service.space.owner.model.OwnerSpaceGenerationModel;
import com.park.spacemng.service.space.owner.model.OwnerSpaceRetrievalModel;
import com.park.spacemng.service.space.owner.model.OwnerSpaceRetrievalResult;
import com.park.spacemng.service.space.owner.model.OwnerSpaceUpdateModel;
import com.park.spacemng.service.space.owner.model.SpaceBookingModel;
import com.park.spacemng.service.space.owner.model.SpaceRequestsResolutionModel;
import com.park.spacemng.service.space.owner.model.SpaceRequestsRetrievalResult;
import com.park.spacemng.service.space.space.SpaceOperationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;

import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OwnerSpaceOperationServiceImpl implements OwnerSpaceOperationService {

	private final OwnerSpaceOperationResourceMapper mapper;

	private final SpaceOperationService spaceOperationService;

	private final BookingOperationService bookingOperationService;

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
	public SpaceRequestsRetrievalResult getSpaceRequests(String batchId) throws ParameterValidationException {
		if (Strings.isBlank(batchId)) {
			throw new ParameterValidationException("batch id is empty or null.");
		}

		BookingRequestsRetrievalResult bookingRequestsRetrievalResult = bookingOperationService
				.retrieveRequests(batchId);
		return mapper.toSpaceRequestsRetrievalResult(bookingRequestsRetrievalResult);
	}

	@Override
	public void resolveSpaceRequests(SpaceRequestsResolutionModel model) throws ParameterValidationException {
		verifyResolutionRequestParameters(model);

		RequestResolution resolution = model.getResolution();
		for (SpaceBookingModel booking : model.getRequests()) {
			verifyRequestParameters(booking);
			resolveRequest(booking.getTrackingCode(), resolution);
		}
	}

	private void verifyResolutionRequestParameters(SpaceRequestsResolutionModel model) throws ParameterValidationException {
		if (model.getRequests().isEmpty()) {
			throw new ParameterValidationException("requests are empty!");
		}
		if (model.getResolution() == null) {
			throw new ParameterValidationException("resolution is null!");
		}
	}

	private void verifyRequestParameters(SpaceBookingModel booking) throws ParameterValidationException {
		if (Objects.isNull(booking.getBatchId())) {
			throw new ParameterValidationException("batch id is null for booking request : " + booking);
		}
		if (Objects.isNull(booking.getTrackingCode())) {
			throw new ParameterValidationException("tracking code is null for booking request : " + booking);
		}
		if (Objects.isNull(booking.getCarId())) {
			throw new ParameterValidationException("car id is null for booking request : " + booking);
		}
		if (Objects.isNull(booking.getDriverId())) {
			throw new ParameterValidationException("driver id is null for booking request : " + booking);
		}
	}

	@Override
	public OwnerSpaceRetrievalResult querySpaces(OwnerSpaceRetrievalModel model) {
		throw new UnsupportedOperationException();
	}

	private void resolveRequest(String trackingCode, RequestResolution resolution) {
		bookingOperationService.resolve(trackingCode, resolution);
	}

}