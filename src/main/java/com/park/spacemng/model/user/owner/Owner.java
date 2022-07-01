package com.park.spacemng.model.user.owner;

import com.park.spacemng.model.user.BirthCertificateInfo;
import com.park.spacemng.model.user.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString(callSuper = true)
@Document(collection = "owners")
public class Owner extends User {

	private String nationalId;

	private BirthCertificateInfo birthCertificateInfo;

	private String fathersName;

}