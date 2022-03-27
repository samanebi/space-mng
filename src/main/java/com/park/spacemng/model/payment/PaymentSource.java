package com.park.spacemng.model.payment;

import lombok.Data;

@Data
public class PaymentSource {

	private String card;

	private String userId;

	private String name;

	private String surname;

}