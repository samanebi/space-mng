package com.park.spacemng.model.dto;

import com.park.spacemng.model.constants.UserStatusDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class UserDetailsDto {

    private String userId;

    private String name;

    private String surname;

    private UserStatusDto userStatus;

}
