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
public class UserRegisterResponse extends GeneralResponse{

    private String userId;

    public UserRegisterResponse() {
        super(ProcessStatus.SUCCESS);
    }

    public UserRegisterResponse(String userId) {
        super(ProcessStatus.SUCCESS);
        this.userId = userId;
    }

}
