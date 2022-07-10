package com.park.spacemng.service.user.owner;

import com.park.spacemng.exception.GeneralException;
import com.park.spacemng.model.user.owner.Owner;
import com.park.spacemng.service.user.owner.model.OwnerRegistrationModel;

public interface OwnerOperationService {

	Owner retrieveOwner(String ownerId) throws GeneralException;

	void registerOwner(OwnerRegistrationModel model);

}