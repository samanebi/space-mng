package com.park.spacemng.api.web.payment;

import com.park.spacemng.model.booking.BookingRequest;
import com.park.spacemng.model.booking.dao.BookingRequestDao;
import com.park.spacemng.model.response.GeneralResponse;
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
import com.park.spacemng.service.user.driver.mapper.DriverOperationServiceMapper;
import com.park.spacemng.util.AbstractBaseIntegrationTest;
import com.park.spacemng.util.Constants;
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

class PaymentResourceIT extends AbstractBaseIntegrationTest {

	private static String driverId;

	private static Driver globalDriver;

	private static String spaceId;

	private static Space globalSpace;

	private static Owner globalOwner;

	private static BookingRequest globalRequest;

	@Autowired
	BookingRequestDao bookingRequestDao;

	@Autowired
	SpaceDao spaceDao;

	@Autowired
	OwnerDao ownerDao;

	@Autowired
	DriverDao driverDao;

	@Autowired
	DriverOperationServiceMapper mapper;

	@BeforeEach
	void beforeEach() {
		spaceDao.deleteAll();
		driverDao.deleteAll();
		ownerDao.deleteAll();
		bookingRequestDao.deleteAll();
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
		globalOwner = ownerDao.insert(owner);
		space.setOwner(globalOwner);
		globalSpace = spaceDao.insert(space);
		spaceId = globalSpace.getId();

		Driver driver = new Driver();
		driver.setStatus(User.Status.ACTIVE);
		driver.setBirthday(100000000L);
		driver.setEmail("sample-driver-email");
		driver.setCellNumber("sample-driver-cell-number");
		driver.setName("sample-driver-name");
		driver.setSurname("sample-driver-surname");
		driver.setGender(Gender.MALE);
		globalDriver = driverDao.insert(driver);

		assertThat(driverDao.findAll()).hasSize(1);
		assertThat(driverDao.findAll().get(0).getId()).isNotNull();
		driverId = driverDao.findAll().get(0).getId();
		assertThat(spaceDao.findAll()).hasSize(1);
		assertThat(spaceDao.findAll().get(0).getId()).isNotNull();
		spaceId = spaceDao.findAll().get(0).getId();
		assertThat(ownerDao.findAll()).hasSize(1);
		assertThat(ownerDao.findAll().get(0).getId()).isNotNull();

		BookingRequest bookingRequest = new BookingRequest();
		bookingRequest.setSpaceId(spaceId);
		bookingRequest.setOwner(globalOwner);
		bookingRequest.setStatus(BookingRequest.Status.ACCEPTED);
		bookingRequest.setCarId("sample-car-id");
		bookingRequest.setDriver(globalDriver);
		bookingRequest.setCarId("sample-car-id");
		bookingRequest.setTrackingCode("sample-tracking-code");
		bookingRequest.setPrice(10000L);
		bookingRequest.setBatchId(globalSpace.getBatchId());
		globalRequest = bookingRequestDao.insert(bookingRequest);
	}

	@Test
	void pay_success() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set(Constants.HEADER_USER_ID, globalOwner.getId());
		HttpEntity entity = new HttpEntity<>(httpHeaders);

		ResponseEntity<GeneralResponse> response = restTemplate
				.exchange(getBaseUrl() + "payment/pay/" + globalRequest.getTrackingCode(),
						HttpMethod.GET, entity, GeneralResponse.class);

		assertThat(response).isNotNull();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(bookingRequestDao.findAll()).hasSize(1);
		BookingRequest bookingRequest = bookingRequestDao.findAll().get(0);
		assertThat(bookingRequest).isNotNull();
		assertThat(bookingRequest.getStatus()).isEqualTo(BookingRequest.Status.PAYED);
	}

	@Test
	void init_success() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set(Constants.HEADER_USER_ID, globalOwner.getId());
		HttpEntity entity = new HttpEntity<>(httpHeaders);

		ResponseEntity<GeneralResponse> response = restTemplate
				.exchange(getBaseUrl() + "payment/init",
						HttpMethod.GET, entity, GeneralResponse.class);

		assertThat(response).isNotNull();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	void verify_success() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set(Constants.HEADER_USER_ID, globalOwner.getId());
		HttpEntity entity = new HttpEntity<>(httpHeaders);

		ResponseEntity<GeneralResponse> response = restTemplate
				.exchange(getBaseUrl() + "payment/verify/" + globalRequest.getTrackingCode(),
						HttpMethod.GET, entity, GeneralResponse.class);

		assertThat(response).isNotNull();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
}