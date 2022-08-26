package com.park.spacemng.model.request;

import com.park.spacemng.model.user.constants.Gender;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Setter
@Getter
@ToString
public class UserRegistrationRequest {

    @NotBlank
    @NotNull
    private String name;

    @NotBlank
    @NotNull
    private String surname;

    @NotBlank
    @NotNull
    private String cellNumber;

    @NotBlank
    @NotNull
    private String email;

    @Positive
    @NotNull
    private long birthday;

    @NotNull
    private Gender gender;

    @NotBlank
    @NotNull
    private String password;

}
