package com.park.spacemng.service.payment;

import com.park.spacemng.model.constants.ProcessStatus;

public interface PaymentOperationService {

	ProcessStatus payRequest(String trackingCode, String userId);

}