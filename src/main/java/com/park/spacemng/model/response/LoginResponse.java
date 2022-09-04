package com.park.spacemng.model.response;

import com.park.spacemng.model.constants.ProcessStatus;
import com.park.spacemng.model.dto.SpaceDetailsDto;
import com.park.spacemng.model.dto.UserDetailsDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class LoginResponse extends GeneralResponse{

    private UserDetailsDto user;

    private List<SpaceDetailsDto> spaces;

    public LoginResponse() {
        super(ProcessStatus.SUCCESS);
    }

    public LoginResponse(UserDetailsDto user,List<SpaceDetailsDto> spaces ) {

        super(ProcessStatus.SUCCESS);
        this.user = user;
    }

}
