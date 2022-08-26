package com.park.spacemng.model.response;

import com.park.spacemng.model.constants.ProcessStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
public class SpaceResolutionResponse extends GeneralResponse {

    private Map<String, Integer> rest;

    public SpaceResolutionResponse() {
        super(ProcessStatus.SUCCESS);
    }

}
