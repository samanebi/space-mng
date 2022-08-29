package com.park.spacemng.api;

import com.park.spacemng.exception.*;
import com.park.spacemng.model.constants.ProcessStatus;
import com.park.spacemng.model.response.GeneralResponse;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class CustomizedExceptionControllerAdviser extends ResponseEntityExceptionHandler {

	@ExceptionHandler(GeneralException.class)
	public final ResponseEntity<GeneralResponse> handleGeneralException(GeneralException ex, WebRequest request) {
		log.error("general exception happened : {}", ex.getMessage());
		return new ResponseEntity<>(new GeneralResponse(ProcessStatus.FAILURE, ex.getMessage()),
				HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<GeneralResponse> handleRegularException(Exception ex, WebRequest request) {
		log.error("exception happened : {}", ex.getMessage());
		ex.printStackTrace();
		return new ResponseEntity<>(new GeneralResponse(ProcessStatus.FAILURE, ex.getMessage()),
				HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(BookingRequestNotFoundException.class)
	public final ResponseEntity<GeneralResponse> handleBookingRequestNotFoundException
			(GeneralException ex, WebRequest request) {
		log.error("general exception happened : {}", ex.getMessage());
		return new ResponseEntity<>(new GeneralResponse(ProcessStatus.BOOKING_REQUEST_NOT_FOUND, ex.getMessage()),
				HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(DriverNotFoundException.class)
	public final ResponseEntity<GeneralResponse> handleDriverNotFoundException(GeneralException ex, WebRequest request) {
		log.error("general exception happened : {}", ex.getMessage());
		return new ResponseEntity<>(new GeneralResponse(ProcessStatus.DRIVER_NOT_FOUND, ex.getMessage()),
				HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(OwnerNotFoundException.class)
	public final ResponseEntity<GeneralResponse> handleOwnerNotFoundException(GeneralException ex, WebRequest request) {
		log.error("general exception happened : {}", ex.getMessage());
		return new ResponseEntity<>(new GeneralResponse(ProcessStatus.OWNER_NOT_FOUND, ex.getMessage()),
				HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(ParameterValidationException.class)
	public final ResponseEntity<GeneralResponse> handleParameterValidationException(GeneralException ex, WebRequest request) {
		log.error("general exception happened : {}", ex.getMessage());
		return new ResponseEntity<>(new GeneralResponse(ProcessStatus.PARAMETER_VALIDATION_FAILED, ex.getMessage()),
				HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(SpaceNotAvailableException.class)
	public final ResponseEntity<GeneralResponse> handleSpaceNotAvailableException(GeneralException ex, WebRequest request) {
		log.error("general exception happened : {}", ex.getMessage());
		return new ResponseEntity<>(new GeneralResponse(ProcessStatus.SPACE_NOT_AVAILABLE, ex.getMessage()),
				HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(SpaceNotFoundException.class)
	public final ResponseEntity<GeneralResponse> handleSpaceNotFoundException(GeneralException ex, WebRequest request) {
		log.error("general exception happened : {}", ex.getMessage());
		return new ResponseEntity<>(new GeneralResponse(ProcessStatus.SPACE_NOT_FOUND, ex.getMessage()),
				HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(StateNotFoundException.class)
	public final ResponseEntity<GeneralResponse> handleStateNotFoundException(GeneralException ex, WebRequest request) {
		log.error("general exception happened : {}", ex.getMessage());
		return new ResponseEntity<>(new GeneralResponse(ProcessStatus.FAILURE, ex.getMessage()),
				HttpStatus.UNPROCESSABLE_ENTITY);
	}

}