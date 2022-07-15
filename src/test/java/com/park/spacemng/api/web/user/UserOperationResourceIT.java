package com.park.spacemng.api.web.user;

import com.park.spacemng.model.request.BirthInfoDetails;
import com.park.spacemng.model.request.DriverRegistrationRequest;
import com.park.spacemng.model.request.OwnerRegistrationRequest;
import com.park.spacemng.model.response.GeneralResponse;
import com.park.spacemng.model.user.User.Status;
import com.park.spacemng.model.user.constants.Gender;
import com.park.spacemng.model.user.driver.Driver;
import com.park.spacemng.model.user.driver.dao.DriverDao;
import com.park.spacemng.model.user.owner.Owner;
import com.park.spacemng.model.user.owner.dao.OwnerDao;
import com.park.spacemng.util.AbstractBaseIntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

class UserOperationResourceIT extends AbstractBaseIntegrationTest {

	@Autowired
	DriverDao driverDao;

	@Autowired
	OwnerDao ownerDao;

	@BeforeEach
	void beforeEach() {
		ownerDao.deleteAll();
		driverDao.deleteAll();
	}

	@Test
	void registerDriver_success() {
		long birthday = 10000L;
		String email = "sample-email";
		Gender gender = Gender.MALE;
		String name = "sample-name";
		String surname = "sample-surname";
		String cellNumber = "sample-cell-number";

		DriverRegistrationRequest request = new DriverRegistrationRequest();
		request.setBirthday(birthday);
		request.setEmail(email);
		request.setGender(gender);
		request.setName(name);
		request.setSurname(surname);
		request.setCellNumber(cellNumber);

		HttpHeaders httpHeaders = new HttpHeaders();
		HttpEntity<DriverRegistrationRequest> entity = new HttpEntity<>(request, httpHeaders);

		ResponseEntity<GeneralResponse> response = restTemplate.exchange(getBaseUrl() + "user/driver/register",
				HttpMethod.POST, entity, GeneralResponse.class);

		assertThat(response).isNotNull();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(driverDao.findAll()).hasSize(1);
		Driver driver = driverDao.findAll().get(0);
		assertThat(driver).isNotNull();
		assertThat(driver.getId()).isNotNull();
		assertThat(driver.getCellNumber()).isNotNull();
		assertThat(driver.getCellNumber()).isEqualTo(cellNumber);
		assertThat(driver.getEmail()).isNotNull();
		assertThat(driver.getEmail()).isEqualTo(email);
		assertThat(driver.getGender()).isNotNull();
		assertThat(driver.getGender()).isEqualTo(gender);
		assertThat(driver.getName()).isNotNull();
		assertThat(driver.getName()).isEqualTo(name);
		assertThat(driver.getStatus()).isNotNull();
		assertThat(driver.getStatus()).isEqualTo(Status.ACTIVE);
		assertThat(driver.getSurname()).isNotNull();
		assertThat(driver.getSurname()).isEqualTo(surname);
		assertThat(driver.getBirthday()).isNotZero();
		assertThat(driver.getBirthday()).isEqualTo(birthday);
	}

	@Test
	void registerOwner_success() {
		long birthday = 10000L;
		String email = "sample-email";
		Gender gender = Gender.MALE;
		String name = "sample-name";
		String surname = "sample-surname";
		String cellNumber = "sample-cell-number";
		String nationalId = "sample-national-id";
		String serialCharacter = "Sample-serial-character";
		String birthCertificateId = "sample-birth-certificate-id";
		String serialNumber = "sample-serial-number";
		String fathersName = "sample-father-name";

		OwnerRegistrationRequest request = new OwnerRegistrationRequest();
		request.setBirthday(birthday);
		request.setEmail(email);
		request.setGender(gender);
		request.setName(name);
		request.setSurname(surname);
		request.setCellNumber(cellNumber);
		request.setNationalId(nationalId);
		BirthInfoDetails infoDetails = new BirthInfoDetails();
		infoDetails.setSerialCharacter(serialCharacter);
		infoDetails.setBirthCertificateId(birthCertificateId);
		infoDetails.setSerialNumber(serialNumber);
		request.setBirthCertificateInfo(infoDetails);
		request.setFathersName(fathersName);

		HttpHeaders httpHeaders = new HttpHeaders();
		HttpEntity<OwnerRegistrationRequest> entity = new HttpEntity<>(request, httpHeaders);

		ResponseEntity<GeneralResponse> response = restTemplate.exchange(getBaseUrl() + "user/owner/register",
				HttpMethod.POST, entity, GeneralResponse.class);

		assertThat(response).isNotNull();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(ownerDao.findAll()).hasSize(1);
		Owner owner = ownerDao.findAll().get(0);
		assertThat(owner).isNotNull();
		assertThat(owner.getId()).isNotNull();
		assertThat(owner.getCellNumber()).isNotNull();
		assertThat(owner.getCellNumber()).isEqualTo(cellNumber);
		assertThat(owner.getEmail()).isNotNull();
		assertThat(owner.getEmail()).isEqualTo(email);
		assertThat(owner.getGender()).isNotNull();
		assertThat(owner.getGender()).isEqualTo(gender);
		assertThat(owner.getName()).isNotNull();
		assertThat(owner.getName()).isEqualTo(name);
		assertThat(owner.getStatus()).isNotNull();
		assertThat(owner.getStatus()).isEqualTo(Status.ACTIVE);
		assertThat(owner.getSurname()).isNotNull();
		assertThat(owner.getSurname()).isEqualTo(surname);
		assertThat(owner.getBirthday()).isNotZero();
		assertThat(owner.getBirthday()).isEqualTo(birthday);
		assertThat(owner.getFathersName()).isNotNull();
		assertThat(owner.getFathersName()).isEqualTo(fathersName);
		assertThat(owner.getNationalId()).isNotNull();
		assertThat(owner.getNationalId()).isEqualTo(nationalId);
		assertThat(owner.getBirthCertificateInfo()).isNotNull();
		assertThat(owner.getBirthCertificateInfo().getBirthCertificateId()).isNotNull();
		assertThat(owner.getBirthCertificateInfo().getBirthCertificateId()).isEqualTo(birthCertificateId);
		assertThat(owner.getBirthCertificateInfo().getSerialCharacter()).isNotNull();
		assertThat(owner.getBirthCertificateInfo().getSerialCharacter()).isEqualTo(serialCharacter);
		assertThat(owner.getBirthCertificateInfo().getSerialCharacter()).isNotNull();
		assertThat(owner.getBirthCertificateInfo().getSerialCharacter()).isEqualTo(serialCharacter);
	}

}