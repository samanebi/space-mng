package com.park.spacemng.api.web.payment;

import javax.validation.constraints.NotBlank;

import com.park.spacemng.model.constants.ProcessStatus;
import com.park.spacemng.model.response.GeneralResponse;
import com.park.spacemng.service.payment.PaymentOperationService;
import com.park.spacemng.util.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/payment")
public class PaymentResource {

	private final PaymentOperationService service;

	@GetMapping(value = "/init", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GeneralResponse> init(@NotBlank @RequestHeader(Constants.HEADER_USER_ID) String userId) {
		return new ResponseEntity<>(new GeneralResponse(ProcessStatus.SUCCESS), HttpStatus.OK);
	}

	@GetMapping(value = "/pay/{trackingCode}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GeneralResponse> pay(@NotBlank @RequestHeader(Constants.HEADER_USER_ID) String userId,
			@NotBlank @PathVariable String trackingCode) {
		return new ResponseEntity<>(new GeneralResponse(service.payRequest(trackingCode, userId)), HttpStatus.OK);
	}

	@GetMapping(value = "/verify/{trackingCode}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GeneralResponse> verify(@NotBlank @RequestHeader(Constants.HEADER_USER_ID) String userId,
			@NotBlank @PathVariable String trackingCode) {
		return new ResponseEntity<>(new GeneralResponse(ProcessStatus.SUCCESS), HttpStatus.OK);
	}

}