package com.park.spacemng.model.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class PaymentRequest {

	@NotBlank
	private String ticket;

}