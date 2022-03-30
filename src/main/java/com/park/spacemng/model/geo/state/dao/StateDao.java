package com.park.spacemng.model.geo.state.dao;

import com.park.spacemng.model.constants.StateName;
import com.park.spacemng.model.geo.state.State;

import org.springframework.stereotype.Repository;

@Repository
public interface StateDao {

	State findStateByCode(StateName name);

}