package com.park.spacemng.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class BirthInfoDetails {

	@NotBlank
	@NotNull
	private String birthCertificateId;

	@NotBlank
	@NotNull
	private String serialNumber;

	@NotBlank
	@NotNull
	private String serialCharacter;

}