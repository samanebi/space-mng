package com.park.spacemng.api.web.space.driver;

import java.util.Date;

import com.park.spacemng.model.constants.ProcessStatus;
import com.park.spacemng.model.geo.state.dao.StateDao;
import com.park.spacemng.model.request.NearbyAvailableSpacesRequest;
import com.park.spacemng.model.request.SpaceBookingRequest;
import com.park.spacemng.model.response.GeneralResponse;
import com.park.spacemng.model.response.NearbyAvailableSpacesResponse;
import com.park.spacemng.model.response.SpaceBookingResponse;
import com.park.spacemng.model.space.space.Space;
import com.park.spacemng.model.space.space.Space.Status;
import com.park.spacemng.model.space.space.dao.SpaceDao;
import com.park.spacemng.model.user.BirthCertificateInfo;
import com.park.spacemng.model.user.User;
import com.park.spacemng.model.user.constants.Gender;
import com.park.spacemng.model.user.driver.Driver;
import com.park.spacemng.model.user.driver.dao.DriverDao;
import com.park.spacemng.model.user.owner.Owner;
import com.park.spacemng.model.user.owner.dao.OwnerDao;
import com.park.spacemng.service.redis.impl.OnlineUserRedisDao;
import com.park.spacemng.util.AbstractBaseIntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

class DriverSpaceOperationResourceIT extends AbstractBaseIntegrationTest {

	private static String driverId;

	private static String spaceId;

	@Autowired
	OnlineUserRedisDao redisDao;

	@Autowired
	StateDao stateDao;

	@Autowired
	SpaceDao spaceDao;

	@Autowired
	DriverDao driverDao;

	@Autowired
	OwnerDao ownerDao;

	@BeforeEach
	void beforeEach() {
		Space space = new Space();
		space.setId("sample-user-id");
		space.setBatchId("sample-batch-id");
		space.setStatus(Status.FREE);
		space.setAddress("sample-address");
		space.setDescription("sample-description");
		space.setTitle("sample-title");
		space.setPosition(new Point(2.0, 2.0));
		Owner owner = new Owner();
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
		Owner savedOwner = ownerDao.insert(owner);
		space.setOwner(savedOwner);
		redisDao.set(savedOwner.getId(), new Date().getTime());
		Space insertedSpace = spaceDao.insert(space);
		spaceId = insertedSpace.getId();

		Driver driver = new Driver();
		driver.setDriverId("sample-driver-id");
		driver.setStatus(User.Status.ACTIVE);
		driver.setBirthday(100000000L);
		driver.setEmail("sample-driver-email");
		driver.setCellNumber("sample-driver-cell-number");
		driver.setName("sample-driver-name");
		driver.setSurname("sample-driver-surname");
		driver.setGender(Gender.MALE);
		driverDao.insert(driver);

		assertThat(driverDao.findAll()).hasSize(1);
		assertThat(driverDao.findAll().get(0).getId()).isNotNull();
		driverId = driverDao.findAll().get(0).getId();
		assertThat(spaceDao.findAll()).hasSize(1);
		assertThat(spaceDao.findAll().get(0).getId()).isNotNull();
		spaceId = spaceDao.findAll().get(0).getId();
		assertThat(ownerDao.findAll()).hasSize(1);
		assertThat(ownerDao.findAll().get(0).getId()).isNotNull();
	}


	@Test
	void bookSpace_success() {
		SpaceBookingRequest request = new SpaceBookingRequest();
		request.setDriverId(driverId);
		request.setAmount(1000L);
		request.setBatchId("sample-batch-id");
		HttpEntity<SpaceBookingRequest> entity = new HttpEntity(request);

		ResponseEntity<SpaceBookingResponse> response = restTemplate.exchange(getBaseUrl() + "spaces/book",
				HttpMethod.POST, entity, SpaceBookingResponse.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody().getStatus()).isNotNull();
		assertThat(response.getBody().getStatus()).isEqualTo(ProcessStatus.SUCCESS);
		assertThat(response.getBody().getSpace()).isNotNull();
		assertThat(response.getBody().getSpace().getSpaceId()).isNotNull();
		assertThat(response.getBody().getSpace().getBatchId()).isNotNull();
	}

	@Test
	void getNearbyAvailableSpaces_success() {
		NearbyAvailableSpacesRequest request = new NearbyAvailableSpacesRequest();
		request.setDriverId(driverId);
		request.setLocation(new Point(1.0, 1.0));
		request.setCarId("sample-car-id");
		HttpEntity<NearbyAvailableSpacesRequest> entity = new HttpEntity(request);

		ResponseEntity<NearbyAvailableSpacesResponse> response = restTemplate.exchange(getBaseUrl() + "spaces",
				HttpMethod.POST, entity, NearbyAvailableSpacesResponse.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody().getStatus()).isNotNull();
		assertThat(response.getBody().getStatus()).isEqualTo(ProcessStatus.SUCCESS);
		assertThat(response.getBody().getSpaces()).hasSize(1);
	}

	@Test
	void evacuate_success() {
		HttpHeaders httpHeaders = new HttpHeaders();
		HttpEntity entity = new HttpEntity(httpHeaders);

		ResponseEntity<GeneralResponse> response = restTemplate.exchange(getBaseUrl() + "spaces/evacuate/" + spaceId,
				HttpMethod.GET, entity, GeneralResponse.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody().getStatus()).isNotNull();
		assertThat(response.getBody().getStatus()).isEqualTo(ProcessStatus.SUCCESS);
	}

}