package com.park.spacemng.service.geo.state.mapper;

import com.park.spacemng.model.geo.state.State;
import com.park.spacemng.service.geo.state.model.StateDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StateGeoOperationServiceMapper {

	StateDetails toStateDetails(State state);

}