package com.park.spacemng.service.user.driver.impl;

import com.park.spacemng.exception.DriverNotFoundException;
import com.park.spacemng.exception.GeneralException;
import com.park.spacemng.exception.InvalidCredentialsException;
import com.park.spacemng.exception.OwnerNotFoundException;
import com.park.spacemng.model.user.User.Status;
import com.park.spacemng.model.user.driver.Driver;
import com.park.spacemng.model.user.driver.dao.DriverDao;
import com.park.spacemng.service.user.driver.mapper.DriverOperationServiceMapper;
import com.park.spacemng.service.user.driver.model.DriverRegistrationModel;
import com.park.spacemng.service.user.owner.model.LoginResult;
import com.park.spacemng.service.user.user.UserOperationService;
import com.park.spacemng.service.user.user.model.UserRegistrationModel;
import com.park.spacemng.service.user.userid.UserIdGenerationService;
import com.park.spacemng.util.ParameterValidator;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DriverOperationServiceImpl implements UserOperationService<Driver> {

	private final DriverDao dao;

	private final DriverOperationServiceMapper mapper;

	private final ParameterValidator parameterValidator;

	@Override
	public LoginResult login(String username, String password) {
		Driver driver = dao.findByCellNumber(username).orElseThrow(()
				-> new DriverNotFoundException("there is no driver with cellNumber " + username));
		boolean result = password.equals(driver.getPassword());
		if (!result){
			throw new InvalidCredentialsException("password not correct");
		}
		return new LoginResult(driver);
	}

	@Override
	public Driver retrieveUser(String driverId) throws GeneralException {
		parameterValidator.requireParameterNotNullOrBlank(driverId);

		Driver driver = dao.findById(driverId).orElseThrow(() ->
				new DriverNotFoundException("driver not found : " + driverId));
		return driver;
	}

	@Override
	public String registerUser(UserRegistrationModel model) {
		Driver driver = mapper.toDriver((DriverRegistrationModel) model);
		driver.setStatus(Status.ACTIVE);
		return dao.save(driver).getId();
	}

}