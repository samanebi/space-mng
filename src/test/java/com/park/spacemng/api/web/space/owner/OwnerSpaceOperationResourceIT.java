package com.park.spacemng.api.web.space.owner;

import java.util.ArrayList;
import java.util.List;

import com.park.spacemng.model.booking.BookingRequest;
import com.park.spacemng.model.booking.dao.BookingRequestDao;
import com.park.spacemng.model.constants.District;
import com.park.spacemng.model.constants.RequestResolution;
import com.park.spacemng.model.constants.StateName;
import com.park.spacemng.model.constants.Town;
import com.park.spacemng.model.request.LocationRequest;
import com.park.spacemng.model.request.SpaceBookingRequest;
import com.park.spacemng.model.request.SpaceGenerationRequest;
import com.park.spacemng.model.request.SpaceResolutionRequest;
import com.park.spacemng.model.request.SpaceUpdateRequest;
import com.park.spacemng.model.response.GeneralResponse;
import com.park.spacemng.model.response.SpaceRetrievalResponse;
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
import com.park.spacemng.service.booking.model.BookingRequestDetails;
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

class OwnerSpaceOperationResourceIT extends AbstractBaseIntegrationTest {

	private static Owner globalOwner;

	private static Driver globalDriver;

	@Autowired
	SpaceDao spaceDao;

	@Autowired
	OwnerDao ownerDao;

	@Autowired
	BookingRequestDao bookingRequestDao;

	@Autowired
	DriverDao driverDao;

	@BeforeEach
	void beforeEach() {
		spaceDao.deleteAll();
		bookingRequestDao.deleteAll();
		Owner owner = new Owner();
		owner.setName("sample-name");
		owner.setSurname("sample-surname");
		owner.setEmail("sample-email");
		owner.setGender(Gender.MALE);
		owner.setStatus(User.Status.ACTIVE);
		owner.setNationalId("sample-national-id");
		owner.setCellNumber("sample-cell-number");
		owner.setFathersName("sample-father-name");
		BirthCertificateInfo birthCertificateInfo = new BirthCertificateInfo();
		birthCertificateInfo.setBirthCertificateId("sample-birth-certificate-id");
		birthCertificateInfo.setSerialCharacter("sample-serial-character");
		birthCertificateInfo.setSerialNumber("sample-serial-id");
		owner.setBirthCertificateInfo(birthCertificateInfo);
		globalOwner = ownerDao.insert(owner);

		Driver driver = new Driver();
		driver.setSurname("sample-driver-surname");
		driver.setGender(Gender.MALE);
		driver.setName("sample-driver-name");
		driver.setEmail("sample-driver-email");
		driver.setCellNumber("Sample-driver-cell-number");
		driver.setBirthday(100000000L);
		globalDriver = driverDao.insert(driver);
	}

	@Test
	void generateSpaces_success() {
		String address = "sample-address";
		String batchId = "sample-batch-id";
		String description = "sample-description";
		String title = "Sample-title";
		int capacity = 5;

		SpaceGenerationRequest request = new SpaceGenerationRequest();
		request.setBatchId(batchId);
		LocationRequest locationRequest = new LocationRequest();
		locationRequest.setLocation(new Point(1.0, 1.0));
		locationRequest.setDistrict(District.FIVE);
		locationRequest.setStateName(StateName.TEHRAN);
		locationRequest.setTown(Town.TEHRAN);
		request.setLocation(locationRequest);
		request.setAddress(address);
		request.setTitle(title);
		request.setCapacity(capacity);
		request.setDescription(description);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(Constants.HEADER_USER_ID, globalOwner.getId());
		HttpEntity<SpaceGenerationRequest> entity = new HttpEntity<>(request, httpHeaders);

		ResponseEntity<GeneralResponse> response = restTemplate.exchange(getBaseUrl() + "spaces/generate",
				HttpMethod.POST, entity, GeneralResponse.class);

		List<Space> spaces = spaceDao.findAll();
		assertThat(response).isNotNull();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(spaces).hasSize(5);
		for (Space space : spaces) {
			assertThat(space.getAddress()).isNotNull();
			assertThat(space.getAddress()).isEqualTo(address);
			assertThat(space.getStatus()).isNotNull();
			assertThat(space.getStatus()).isEqualTo(Status.FREE);
			assertThat(space.getBatchId()).isNotNull();
			assertThat(space.getBatchId()).isEqualTo(batchId);
			assertThat(space.getDescription()).isNotNull();
			assertThat(space.getDescription()).isEqualTo(description);
			assertThat(space.getTitle()).isNotNull();
			assertThat(space.getTitle()).isEqualTo(title);
			assertThat(space.getOwner()).isNotNull();
			assertThat(space.getOwner().getFathersName()).isNotNull();
			assertThat(space.getOwner().getFathersName()).isEqualTo("sample-father-name");
			assertThat(space.getOwner().getName()).isNotNull();
			assertThat(space.getOwner().getName()).isEqualTo("sample-name");
			assertThat(space.getOwner().getSurname()).isEqualTo("sample-surname");
			assertThat(space.getOwner().getStatus()).isEqualTo(User.Status.ACTIVE);
		}
	}

	@Test
	void updateSpace_success() {
		String batchId = "sample-batch-id";

		Space space = new Space();
		space.setPosition(new Point(2.0, 2.0));
		space.setDescription("wrong-description");
		space.setTitle("wrong-title");
		space.setAddress("wrong-address");
		space.setBatchId(batchId);
		space.setStatus(Status.FREE);
		spaceDao.insert(space);

		String address = "sample-address";
		String description = "sample-description";
		String title = "Sample-title";
		int capacity = 5;

		SpaceUpdateRequest request = new SpaceUpdateRequest();
		request.setBatchId(batchId);
		LocationRequest locationRequest = new LocationRequest();
		locationRequest.setLocation(new Point(1.0, 1.0));
		locationRequest.setDistrict(District.FIVE);
		locationRequest.setStateName(StateName.TEHRAN);
		locationRequest.setTown(Town.TEHRAN);
		request.setLocation(locationRequest);
		request.setAddress(address);
		request.setTitle(title);
		request.setCapacity(capacity);
		request.setDescription(description);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(Constants.HEADER_USER_ID, globalOwner.getId());
		HttpEntity<SpaceUpdateRequest> entity = new HttpEntity<>(request, httpHeaders);

		ResponseEntity<GeneralResponse> response = restTemplate.exchange(getBaseUrl() + "spaces/space/update",
				HttpMethod.POST, entity, GeneralResponse.class);

		List<Space> spaces = spaceDao.findAll();
		assertThat(response).isNotNull();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(spaces).hasSize(5);
		for (Space generatedSpace : spaces) {
			assertThat(generatedSpace.getAddress()).isNotNull();
			assertThat(generatedSpace.getAddress()).isEqualTo(address);
			assertThat(generatedSpace.getStatus()).isNotNull();
			assertThat(generatedSpace.getStatus()).isEqualTo(Status.FREE);
			assertThat(generatedSpace.getBatchId()).isNotNull();
			assertThat(generatedSpace.getBatchId()).isEqualTo(batchId);
			assertThat(generatedSpace.getDescription()).isNotNull();
			assertThat(generatedSpace.getDescription()).isEqualTo(description);
			assertThat(generatedSpace.getTitle()).isNotNull();
			assertThat(generatedSpace.getTitle()).isEqualTo(title);
		}
	}

	@Test
	void getSpaceRequests_success() {
		String batchId = "sample-batch-id";
		String trackingCode = "sample-booking-request-tracking-code";
		long amount = 10000L;

		Space space = new Space();
		space.setPosition(new Point(2.0, 2.0));
		space.setDescription("wrong-description");
		space.setTitle("wrong-title");
		space.setAddress("wrong-address");
		space.setBatchId(batchId);
		space.setStatus(Status.FREE);
		space.setOwner(globalOwner);
		Space insertedSpace = spaceDao.insert(space);

		BookingRequest bookingRequest = new BookingRequest();
		bookingRequest.setSpaceId(insertedSpace.getId());
		bookingRequest.setOwner(globalOwner);
		bookingRequest.setStatus(BookingRequest.Status.INITIATED);
		bookingRequest.setCarId("sample-car-id");
		bookingRequest.setDriver(globalDriver);
		bookingRequest.setCarId("sample-car-id");
		bookingRequest.setTrackingCode(trackingCode);
		bookingRequest.setAmount(amount);
		bookingRequest.setBatchId(space.getBatchId());
		BookingRequest insertedBookingRequest = bookingRequestDao.insert(bookingRequest);

		HttpHeaders httpHeaders = new HttpHeaders();
		ResponseEntity<SpaceRetrievalResponse> response = restTemplate.exchange(getBaseUrl()
						+ "spaces/space/requests/"
						+ insertedBookingRequest.getBatchId(),
				HttpMethod.GET, new HttpEntity<>(httpHeaders), SpaceRetrievalResponse.class);

		assertThat(response).isNotNull();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		SpaceRetrievalResponse body = response.getBody();
		assertThat(body).isNotNull();
		assertThat(body.getRequests()).hasSize(1);
		BookingRequestDetails bookingRequestDetails = body.getRequests().get(0);
		assertThat(bookingRequestDetails.getBatchId()).isNotNull();
		assertThat(bookingRequestDetails.getBatchId()).isEqualTo(batchId);
		assertThat(bookingRequestDetails.getResolution()).isNull();
		assertThat(bookingRequestDetails.getTrackingCode()).isEqualTo(trackingCode);
		assertThat(bookingRequestDetails.getAmount()).isNotZero();
		assertThat(bookingRequestDetails.getAmount()).isEqualTo(amount);
	}

	@Test
	void resolveSpaceRequests() {
		String batchId = "sample-batch-id";
		String trackingCode = "sample-booking-request-tracking-code";
		long amount = 10000L;

		Space space = new Space();
		space.setPosition(new Point(2.0, 2.0));
		space.setDescription("wrong-description");
		space.setTitle("wrong-title");
		space.setAddress("wrong-address");
		space.setBatchId(batchId);
		space.setStatus(Status.FREE);
		space.setOwner(globalOwner);
		Space insertedSpace = spaceDao.insert(space);

		BookingRequest bookingRequest = new BookingRequest();
		bookingRequest.setSpaceId(insertedSpace.getId());
		bookingRequest.setOwner(globalOwner);
		bookingRequest.setStatus(BookingRequest.Status.INITIATED);
		bookingRequest.setCarId("sample-car-id");
		bookingRequest.setDriver(globalDriver);
		bookingRequest.setCarId("sample-car-id");
		bookingRequest.setTrackingCode(trackingCode);
		bookingRequest.setAmount(amount);
		bookingRequest.setBatchId(space.getBatchId());
		BookingRequest insertedBookingRequest = bookingRequestDao.insert(bookingRequest);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(Constants.HEADER_USER_ID, globalOwner.getId());

		SpaceResolutionRequest request = new SpaceResolutionRequest();
		SpaceBookingRequest spaceBookingRequest = new SpaceBookingRequest();
		spaceBookingRequest.setDriverId(globalDriver.getId());
		spaceBookingRequest.setBatchId(batchId);
		spaceBookingRequest.setTrackingCode(trackingCode);
		spaceBookingRequest.setResolution(RequestResolution.APPROVE);
		spaceBookingRequest.setAmount(amount);
		List<SpaceBookingRequest> bookingRequests = new ArrayList<>();
		bookingRequests.add(spaceBookingRequest);
		request.setRequests(bookingRequests);

		ResponseEntity<SpaceRetrievalResponse> response = restTemplate.exchange(getBaseUrl()
						+ "spaces/space/requests/resolve/"
						+ insertedBookingRequest.getBatchId(),
				HttpMethod.POST, new HttpEntity<>(request, httpHeaders), SpaceRetrievalResponse.class);

		assertThat(response).isNotNull();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		List<BookingRequest> allBookingRequests = bookingRequestDao.findAll();
		assertThat(allBookingRequests).hasSize(1);
		BookingRequest retrievedBookingRequest = allBookingRequests.get(0);
		assertThat(retrievedBookingRequest.getStatus()).isEqualTo(BookingRequest.Status.ACCEPTED);
	}

}