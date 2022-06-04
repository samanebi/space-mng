package com.park.spacemng.api.web.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.park.spacemng.model.constants.ProcessStatus;
import com.park.spacemng.model.response.GeneralResponse;
import com.park.spacemng.service.user.online.OnlineUserService;
import com.park.spacemng.util.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/user")
public class OnlineUserResource {

	private final OnlineUserService service;

	@GetMapping(value = "/heart-beat", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GeneralResponse> heartBeat(@NotNull @NotBlank
	@RequestHeader(Constants.HEADER_USER_ID) String userId) {
		service.processHeartBeat(userId);
		return new ResponseEntity<>(new GeneralResponse(ProcessStatus.SUCCESS), HttpStatus.OK);
	}

}