package com.park.spacemng.service.user.owner.model;

import com.park.spacemng.model.constants.UserStatus;
import com.park.spacemng.model.dto.SpaceDetailsDto;
import com.park.spacemng.model.user.User;
import lombok.*;

import java.util.Collections;
import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoginResult {

    private User user;

    private List<SpaceDetailsDto> spaces;

    private UserStatus userStatus;

    public LoginResult(User user){
        this.spaces = Collections.emptyList();
        this.user = user;
    }
}
