package com.park.spacemng.service.space.owner;

import com.park.spacemng.service.space.owner.model.OnlineOwnerRetrievalModel;
import com.park.spacemng.service.space.owner.model.OnlineOwnerRetrievalResult;

public interface OnlineOwnerOperationService {

	// todo : complete repo
	OnlineOwnerRetrievalResult isOnline(OnlineOwnerRetrievalModel model);

}