package com.park.spacemng.api;

import com.park.spacemng.exception.GeneralException;
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
		return new ResponseEntity<>(new GeneralResponse(ProcessStatus.FAILURE, ex.getMessage()),
				HttpStatus.UNPROCESSABLE_ENTITY);
	}

}