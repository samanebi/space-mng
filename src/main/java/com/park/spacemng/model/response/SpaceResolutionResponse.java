package com.park.spacemng.model.response;

import com.park.spacemng.model.constants.ProcessStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Setter
@Getter
public class SpaceResolutionResponse extends GeneralResponse {

    private List<RestDetails> rest;

    public SpaceResolutionResponse() {
        super(ProcessStatus.SUCCESS);
    }

}
