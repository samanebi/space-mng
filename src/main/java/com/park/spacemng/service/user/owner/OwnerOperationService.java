package com.park.spacemng.service.user.owner;

import java.util.Optional;

import com.park.spacemng.service.user.owner.model.OwnerInfo;

public interface OwnerOperationService {

	// todo :  remember to implement
	Optional<OwnerInfo> retrieveOwner(String ownerId);

}