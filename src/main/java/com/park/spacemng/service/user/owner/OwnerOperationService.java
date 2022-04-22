package com.park.spacemng.service.user.owner;

import com.park.spacemng.exception.GeneralException;
import com.park.spacemng.service.user.owner.model.OwnerInfo;

public interface OwnerOperationService {

	// todo :  remember to implement
	OwnerInfo retrieveOwner(String ownerId) throws GeneralException;

}