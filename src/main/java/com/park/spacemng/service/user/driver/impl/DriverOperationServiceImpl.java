package com.park.spacemng.service.user.driver.impl;

import com.park.spacemng.exception.DriverNotFoundException;
import com.park.spacemng.exception.GeneralException;
import com.park.spacemng.exception.OwnerNotFoundException;
import com.park.spacemng.model.user.User.Status;
import com.park.spacemng.model.user.driver.Driver;
import com.park.spacemng.model.user.driver.dao.DriverDao;
import com.park.spacemng.service.user.driver.mapper.DriverOperationServiceMapper;
import com.park.spacemng.service.user.driver.model.DriverRegistrationModel;
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
	public void login(String username, String password) {
		password.equals(dao.findByCellNumber(username).orElseThrow(()
				-> new DriverNotFoundException("there is no driver with cellNumber " + username)).getPassword());
	}

	@Override
	public Driver retrieveUser(String driverId) throws GeneralException {
		parameterValidator.requireParameterNotNullOrBlank(driverId);

		Driver driver = dao.findById(driverId).orElseThrow(() ->
				new DriverNotFoundException("driver not found : " + driverId));
		return driver;
	}

	@Override
	public void registerUser(UserRegistrationModel model) {
		Driver driver = mapper.toDriver((DriverRegistrationModel) model);
		driver.setStatus(Status.ACTIVE);
		dao.save(driver);
	}

}