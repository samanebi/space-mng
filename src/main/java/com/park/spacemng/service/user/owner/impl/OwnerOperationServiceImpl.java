package com.park.spacemng.service.user.owner.impl;

import com.park.spacemng.exception.GeneralException;
import com.park.spacemng.exception.OwnerNotFoundException;
import com.park.spacemng.model.user.owner.Owner;
import com.park.spacemng.model.user.owner.dao.OwnerDao;
import com.park.spacemng.service.user.owner.OwnerOperationService;
import com.park.spacemng.service.user.owner.mapper.OwnerOperationServiceMapper;
import com.park.spacemng.service.user.owner.model.OwnerInfo;
import com.park.spacemng.util.ParameterValidator;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OwnerOperationServiceImpl implements OwnerOperationService {

	private final OwnerDao dao;

	private final OwnerOperationServiceMapper mapper;

	private final ParameterValidator parameterValidator;

	@Override
	public OwnerInfo retrieveOwner(String ownerId) throws GeneralException {
		parameterValidator.requireParameterNotNullOrBlank(ownerId);

		Owner ownerOptional = dao.findById(ownerId).orElseThrow(() ->
				new OwnerNotFoundException("owner not found : " + ownerId));
		return mapper.toOwnerInfo(ownerOptional);
	}

}