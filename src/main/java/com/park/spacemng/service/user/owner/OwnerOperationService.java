package com.park.spacemng.service.user.owner;

import com.park.spacemng.exception.GeneralException;
import com.park.spacemng.service.user.owner.model.OwnerInfo;
import com.park.spacemng.service.user.owner.model.OwnerRegistrationModel;

public interface OwnerOperationService {

	OwnerInfo retrieveOwner(String ownerId) throws GeneralException;

	void registerOwner(OwnerRegistrationModel model);

}