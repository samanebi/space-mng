package com.park.spacemng.model.payment;

import lombok.Data;

@Data
public class ProviderResponse {

	private String trackingCode;

	private String message;

	private long responseCode;

}