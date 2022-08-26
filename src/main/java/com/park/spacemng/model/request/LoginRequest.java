package com.park.spacemng.model.request;

import com.park.spacemng.model.constants.UserTypeDto;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequest {

    private String cellNumber;

    private String password;

    private UserTypeDto userType;

}
