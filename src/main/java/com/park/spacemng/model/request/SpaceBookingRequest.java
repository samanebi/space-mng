package com.park.spacemng.model.request;

import com.park.spacemng.model.constants.CarType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@ToString
public class SpaceBookingRequest {

    @NotNull
    @NotBlank
    private String batchId;

    private String driverId;

    @NotNull
    private CarType carSize;

}
