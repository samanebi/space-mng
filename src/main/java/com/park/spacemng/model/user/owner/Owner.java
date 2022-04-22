package com.park.spacemng.model.user.owner;

import com.park.spacemng.model.user.BirthCertificateInfo;
import com.park.spacemng.model.user.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class Owner extends User {

	private String nationalId;

	private BirthCertificateInfo birthCertificateInfo;

	private String fathersName;

}