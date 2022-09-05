package com.park.spacemng.service.user.owner.impl;

import com.park.spacemng.exception.GeneralException;
import com.park.spacemng.exception.InvalidCredentialsException;
import com.park.spacemng.exception.OwnerNotFoundException;
import com.park.spacemng.model.constants.UserStatus;
import com.park.spacemng.model.dto.SpaceDetailsDto;
import com.park.spacemng.model.space.space.Space;
import com.park.spacemng.model.space.space.dao.SpaceDao;
import com.park.spacemng.model.user.User.Status;
import com.park.spacemng.model.user.owner.Owner;
import com.park.spacemng.model.user.owner.dao.OwnerDao;
import com.park.spacemng.service.space.owner.OnlineOwnerOperationService;
import com.park.spacemng.service.user.owner.mapper.OwnerOperationServiceMapper;
import com.park.spacemng.service.user.owner.model.LoginResult;
import com.park.spacemng.service.user.owner.model.OwnerRegistrationModel;
import com.park.spacemng.service.user.user.UserOperationService;
import com.park.spacemng.service.user.user.model.UserRegistrationModel;
import com.park.spacemng.service.user.userid.UserIdGenerationService;
import com.park.spacemng.util.ParameterValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.math.ec.custom.sec.SecT113Field;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class OwnerOperationServiceImpl implements UserOperationService<Owner> {

	private final OwnerDao dao;

	private final OwnerOperationServiceMapper mapper;

	private final ParameterValidator parameterValidator;

	private final OnlineOwnerOperationService onlineOwnerOperationService;

	private final SpaceDao spaceDao;

	@Override
	public LoginResult login(String username, String password) {
		Owner owner = dao.findByCellNumber(username).orElseThrow(()
				-> new OwnerNotFoundException("there is no owner with cellNumber " + username));
		boolean result = password.equals(owner.getPassword());
		if (!result){
			throw new InvalidCredentialsException("password not correct");
		}
		List<SpaceDetailsDto> spaces = new LinkedList<>();
		List<Space> spacesDaoByOwnerId = spaceDao.findByOwnerId(owner.getId());
		spacesDaoByOwnerId.forEach(space -> {
			if (spaces.stream()
					.noneMatch(spaceDaoByOwnerId -> space.getBatchId().equals(spaceDaoByOwnerId.getBatchId()))){
				spaces.add(mapper.toSpaceDetailsDto(space, spaceDao.countAllByBatchIdAndStatus(space.getBatchId()
						, Space.Status.FREE)));
			}
		});
		return new LoginResult(owner, spaces, getStatus(owner));
	}

	private UserStatus getStatus(Owner owner) {
		return onlineOwnerOperationService.isOnline(owner.getId()) ? UserStatus.ONLINE : UserStatus.OFFLINE;
	}

	@Override
	public Owner retrieveUser(String ownerId) throws GeneralException {
		log.info("going to retrieve owner : {}", ownerId);
		parameterValidator.requireParameterNotNullOrBlank(ownerId);

		return dao.findById(ownerId).orElseThrow(() ->
				new OwnerNotFoundException("owner not found : " + ownerId));
	}

	@Override
	public String registerUser(UserRegistrationModel model) {
		Owner owner = mapper.toOwner((OwnerRegistrationModel) model);
		owner.setStatus(Status.ACTIVE);
		//owner.setOwnerId(userIdGenerationService.generate());
		return dao.insert(owner).getId();
	}

}