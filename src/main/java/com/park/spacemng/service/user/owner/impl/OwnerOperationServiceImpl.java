package com.park.spacemng.service.user.owner.impl;

import com.park.spacemng.exception.GeneralException;
import com.park.spacemng.exception.OwnerNotFoundException;
import com.park.spacemng.model.user.User.Status;
import com.park.spacemng.model.user.owner.Owner;
import com.park.spacemng.model.user.owner.dao.OwnerDao;
import com.park.spacemng.service.user.owner.mapper.OwnerOperationServiceMapper;
import com.park.spacemng.service.user.owner.model.OwnerRegistrationModel;
import com.park.spacemng.service.user.user.UserOperationService;
import com.park.spacemng.service.user.user.model.UserRegistrationModel;
import com.park.spacemng.service.user.userid.UserIdGenerationService;
import com.park.spacemng.util.ParameterValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OwnerOperationServiceImpl implements UserOperationService<Owner> {

	private final OwnerDao dao;

	private final OwnerOperationServiceMapper mapper;

	private final ParameterValidator parameterValidator;

	@Override
	public void login(String username, String password) {
		password.equals(dao.findByCellNumber(username).orElseThrow(()
				-> new OwnerNotFoundException("there is no owner with cellNumber " + username)).getPassword());
	}

	@Override
	public Owner retrieveUser(String ownerId) throws GeneralException {
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