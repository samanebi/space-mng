package com.park.spacemng.service.payment.impl;

import com.park.spacemng.model.constants.ProcessStatus;
import com.park.spacemng.service.booking.BookingOperationService;
import com.park.spacemng.service.payment.PaymentOperationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentOperationServiceImpl implements PaymentOperationService {

	private final BookingOperationService bookingOperationService;

	@Override
	public ProcessStatus payRequest(String trackingCode, String userId) {
		bookingOperationService.pay(trackingCode);
		return ProcessStatus.SUCCESS;
	}

}