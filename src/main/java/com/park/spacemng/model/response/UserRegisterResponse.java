package com.park.spacemng.model.response;

import com.park.spacemng.model.constants.ProcessStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.naming.ldap.PagedResultsControl;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class UserRegisterResponse extends GeneralResponse{

    private String userId;

    public UserRegisterResponse() {
        super(ProcessStatus.SUCCESS);
    }

}
