package com.park.spacemng.api.web.user;

import javax.validation.constraints.NotNull;

import com.park.spacemng.api.web.user.mapper.UserOperationResourceMapper;
import com.park.spacemng.model.constants.ProcessStatus;
import com.park.spacemng.model.request.DriverRegistrationRequest;
import com.park.spacemng.model.request.OwnerRegistrationRequest;
import com.park.spacemng.model.response.GeneralResponse;
import com.park.spacemng.service.user.driver.DriverOperationService;
import com.park.spacemng.service.user.driver.model.DriverRegistrationModel;
import com.park.spacemng.service.user.owner.OwnerOperationService;
import com.park.spacemng.service.user.owner.model.OwnerRegistrationModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserOperationResource {

	private final DriverOperationService driverOperationService;

	private final OwnerOperationService ownerOperationService;

	private final UserOperationResourceMapper mapper;

	@PostMapping(value = "/driver/register", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GeneralResponse> registerDriver(@NotNull @RequestBody DriverRegistrationRequest request) {
		DriverRegistrationModel model = mapper.toDriverRegistrationModel(request);
		driverOperationService.registerDriver(model);
		return new ResponseEntity<>(new GeneralResponse(ProcessStatus.SUCCESS), HttpStatus.OK);
	}

	@PostMapping(value = "/owner/register", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GeneralResponse> registerOwner(@NotNull @RequestBody OwnerRegistrationRequest request) {
		OwnerRegistrationModel model = mapper.toOwnerRegistrationModel(request);
		ownerOperationService.registerOwner(model);
		return new ResponseEntity<>(new GeneralResponse(ProcessStatus.SUCCESS), HttpStatus.OK);
	}

}