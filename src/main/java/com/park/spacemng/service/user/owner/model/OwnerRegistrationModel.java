package com.park.spacemng.service.user.owner.model;

import com.park.spacemng.model.user.BirthCertificateInfo;
import com.park.spacemng.model.user.constants.Gender;
import com.park.spacemng.service.user.user.model.UserRegistrationModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class OwnerRegistrationModel extends UserRegistrationModel {

	private String nationalId;

	private BirthCertificateInfo birthCertificateInfo;

	private String fathersName;

}