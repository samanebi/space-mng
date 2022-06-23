package com.park.spacemng.api.web.space.driver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.park.spacemng.model.constants.District;
import com.park.spacemng.model.constants.StateName;
import com.park.spacemng.model.constants.Town;
import com.park.spacemng.model.geo.state.dao.StateDao;
import com.park.spacemng.model.space.SpaceLocation;
import com.park.spacemng.model.space.space.Space;
import com.park.spacemng.model.space.space.Space.Status;
import com.park.spacemng.model.space.space.dao.SpaceDao;
import com.park.spacemng.model.user.BirthCertificateInfo;
import com.park.spacemng.model.user.User;
import com.park.spacemng.model.user.constants.Gender;
import com.park.spacemng.model.user.owner.Owner;
import com.park.spacemng.service.redis.impl.OnlineUserRedisDao;
import com.park.spacemng.util.AbstractBaseIntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

class DriverSpaceOperationResourceIT extends AbstractBaseIntegrationTest {

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	OnlineUserRedisDao redisDao;

	@Autowired
	StateDao stateDao;

	@Autowired
	SpaceDao dao;

	@BeforeEach
	void beforeEach() {
		Space space = new Space();
		space.setId("sample-user-id");
		space.setBatchId("sample-batch-id");
		space.setStatus(Status.FREE);
		space.setAddress("sample-address");
		space.setDescription("sample-description");
		space.setTitle("sample-title");
		SpaceLocation location = new SpaceLocation();
		location.setTown(Town.TEHRAN);
		location.setDistrict(District.FIVE);
		location.setStateName(StateName.TEHRAN);
		space.setLocation(location);
		Owner owner = new Owner();
		owner.setOwnerId("sample-owner-id");
		owner.setName("sample-owner-name");
		owner.setCellNumber("sample-cell-number");
		owner.setStatus(User.Status.ACTIVE);
		owner.setFathersName("Sample-owner-father-name");
		owner.setNationalId("sample-national-id");
		owner.setEmail("sample-email");
		owner.setGender(Gender.MALE);
		owner.setSurname("sample-owner-surname");
		owner.setBirthday(100000000L);
		BirthCertificateInfo birthCertificateInfo = new BirthCertificateInfo();
		birthCertificateInfo.setBirthCertificateId("sample-owner-birth-certificate-id");
		birthCertificateInfo.setSerialCharacter("sample-owner-serial-character");
		birthCertificateInfo.setSerialNumber("sample-owner-serial-number");
		owner.setBirthCertificateInfo(birthCertificateInfo);
		space.setOwner(owner);
		dao.save(space);
	}


	@Test
	void bookSpace() {
	}

}