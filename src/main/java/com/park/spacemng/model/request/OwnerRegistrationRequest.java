package com.park.spacemng.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.park.spacemng.model.user.constants.Gender;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class OwnerRegistrationRequest {

	@NotBlank
	@NotNull
	private String name;

	@NotBlank
	@NotNull
	private String surname;

	@NotBlank
	@NotNull
	private String cellNumber;

	@NotBlank
	@NotNull
	private String email;

	@Positive
	@NotNull
	private long birthday;

	@NotNull
	private Gender gender;

	@NotBlank
	@NotNull
	private String nationalId;

	@NotNull
	private BirthInfoDetails birthCertificateInfo;

	@NotBlank
	@NotNull
	private String fathersName;

}