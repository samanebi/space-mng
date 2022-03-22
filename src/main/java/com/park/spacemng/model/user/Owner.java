package com.park.spacemng.model.user;

import lombok.Data;

@Data
public class Owner extends User {

	private String ownerId;

	private String nationalId;

	private BirthCertificateInfo birthCertificateInfo;

	private String fathersName;

}