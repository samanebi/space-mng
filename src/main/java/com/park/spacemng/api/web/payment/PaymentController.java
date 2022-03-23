package com.park.spacemng.api.web.payment;

import javax.validation.constraints.NotBlank;

import com.park.spacemng.model.constants.ProcessStatus;
import com.park.spacemng.model.response.GeneralResponse;
import com.park.spacemng.util.Constants;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/payments")
@Slf4j
public class PaymentController {

	@GetMapping(value = "/pay/ipg/{ticket}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GeneralResponse> pay(@NotBlank @RequestHeader(Constants.HEADER_USER_ID) String userId,
			@NotBlank @RequestHeader(Constants.HEADER_X_FORWARD_FOR) String userIp,
			@NotBlank @PathVariable String ticket) {
		return new ResponseEntity<>(new GeneralResponse(ProcessStatus.SUCCESS), HttpStatus.OK);
	}

}