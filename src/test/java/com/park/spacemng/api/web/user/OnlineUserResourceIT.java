package com.park.spacemng.api.web.user;

import com.park.spacemng.model.response.GeneralResponse;
import com.park.spacemng.model.user.User;
import com.park.spacemng.model.user.constants.Gender;
import com.park.spacemng.model.user.owner.Owner;
import com.park.spacemng.model.user.owner.dao.OwnerDao;
import com.park.spacemng.service.redis.impl.OnlineUserRedisDao;
import com.park.spacemng.util.AbstractBaseIntegrationTest;
import com.park.spacemng.util.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

class OnlineUserResourceIT extends AbstractBaseIntegrationTest {

	private static Owner globalOwner;

	@Autowired
	OwnerDao ownerDao;

	@Autowired
	OnlineUserRedisDao redisDao;

	@BeforeEach
	void beforeEach() {
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
		globalOwner = ownerDao.insert(owner);
	}

	@Test
	void heartBeat_success() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set(Constants.HEADER_USER_ID, globalOwner.getId());
		HttpEntity entity = new HttpEntity(httpHeaders);

		ResponseEntity<GeneralResponse> response = restTemplate.exchange(getBaseUrl() + "user/heart-beat",
				HttpMethod.GET, entity, GeneralResponse.class);

		Long heartbeat = redisDao.get(globalOwner.getId());

		assertThat(response).isNotNull();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(heartbeat).isNotZero();
	}

}