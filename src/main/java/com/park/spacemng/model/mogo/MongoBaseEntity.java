package com.park.spacemng.model.mogo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.springframework.data.annotation.Id;

@Getter
@Setter
@ToString
public class MongoBaseEntity implements Serializable {

	private static final long serialVersionUID = -8152521988572821258L;

	@Id
	private String id;

}