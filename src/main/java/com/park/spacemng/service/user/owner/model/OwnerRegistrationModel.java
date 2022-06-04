package com.park.spacemng.service.user.owner.model;

import com.park.spacemng.model.user.BirthCertificateInfo;
import com.park.spacemng.model.user.constants.Gender;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class OwnerRegistrationModel {

	private String name;

	private String surname;

	private String cellNumber;

	private String email;

	private long birthday;

	private Gender gender;

	private String nationalId;

	private BirthCertificateInfo birthCertificateInfo;

	private String fathersName;

}