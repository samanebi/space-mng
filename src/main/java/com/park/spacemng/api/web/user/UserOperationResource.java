package com.park.spacemng.api.web.user;

import com.park.spacemng.api.web.user.mapper.UserOperationResourceMapper;
import com.park.spacemng.model.constants.ProcessStatus;
import com.park.spacemng.model.request.DriverRegistrationRequest;
import com.park.spacemng.model.request.LoginRequest;
import com.park.spacemng.model.request.OwnerRegistrationRequest;
import com.park.spacemng.model.response.GeneralResponse;
import com.park.spacemng.model.response.UserRegisterResponse;
import com.park.spacemng.model.user.constants.UserType;
import com.park.spacemng.model.user.driver.Driver;
import com.park.spacemng.model.user.owner.Owner;
import com.park.spacemng.service.user.driver.model.DriverRegistrationModel;
import com.park.spacemng.service.user.owner.model.OwnerRegistrationModel;
import com.park.spacemng.service.user.user.UserOperationService;
import com.park.spacemng.service.user.user.UserOperationStrategy;
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

import javax.validation.constraints.NotNull;

@Slf4j
@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserOperationResource {

	private final UserOperationStrategy userOperationStrategy;
	private final UserOperationResourceMapper mapper;

	@PostMapping(value = "/driver/register", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserRegisterResponse> registerDriver(@NotNull @RequestBody DriverRegistrationRequest request) {
		UserOperationService<Driver> userOperationService = userOperationStrategy.get(UserType.DRIVER);
		DriverRegistrationModel model = mapper.toDriverRegistrationModel(request);
		return new ResponseEntity<>(new UserRegisterResponse(userOperationService.registerUser(model)), HttpStatus.OK);
	}

	@PostMapping(value = "/owner/register", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserRegisterResponse> registerOwner(@NotNull @RequestBody OwnerRegistrationRequest request) {
		UserOperationService<Owner> userOperationService = userOperationStrategy.get(UserType.OWNER);
		OwnerRegistrationModel model = mapper.toOwnerRegistrationModel(request);
		return new ResponseEntity<>(new UserRegisterResponse(userOperationService.registerUser(model)), HttpStatus.OK);
	}

	@PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GeneralResponse> login(@NotNull @RequestBody LoginRequest request) {
		UserOperationService userOperationService = userOperationStrategy.get(mapper.toUserType(request.getUserType()));
		userOperationService.login(request.getCellNumber(), request.getPassword());
		return new ResponseEntity<>(new GeneralResponse(ProcessStatus.SUCCESS), HttpStatus.OK);
	}

}