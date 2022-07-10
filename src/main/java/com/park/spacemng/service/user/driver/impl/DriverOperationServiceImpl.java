package com.park.spacemng.service.user.driver.impl;

import com.park.spacemng.exception.DriverNotFoundException;
import com.park.spacemng.exception.GeneralException;
import com.park.spacemng.model.user.User.Status;
import com.park.spacemng.model.user.driver.Driver;
import com.park.spacemng.model.user.driver.dao.DriverDao;
import com.park.spacemng.service.user.driver.DriverOperationService;
import com.park.spacemng.service.user.driver.mapper.DriverOperationServiceMapper;
import com.park.spacemng.service.user.driver.model.DriverInfo;
import com.park.spacemng.service.user.driver.model.DriverRegistrationModel;
import com.park.spacemng.service.user.userid.UserIdGenerationService;
import com.park.spacemng.util.ParameterValidator;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DriverOperationServiceImpl implements DriverOperationService {

	private final DriverDao dao;

	private final DriverOperationServiceMapper mapper;

	private final ParameterValidator parameterValidator;

	private final UserIdGenerationService userIdGenerationService;

	@Override

	public DriverInfo retrieveDriver(String driverId) throws GeneralException {
		parameterValidator.requireParameterNotNullOrBlank(driverId);

		Driver driver = dao.findById(driverId).orElseThrow(() ->
				new DriverNotFoundException("driver not found : " + driverId));
		return mapper.toDriverInfo(driver);
	}

	@Override
	public void registerDriver(DriverRegistrationModel model) {
		Driver driver = mapper.toDriver(model);
		driver.setStatus(Status.ACTIVE);
		dao.save(driver);
	}

}