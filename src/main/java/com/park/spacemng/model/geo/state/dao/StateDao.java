package com.park.spacemng.model.geo.state.dao;

import java.util.Optional;

import com.park.spacemng.model.constants.StateName;
import com.park.spacemng.model.geo.state.State;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateDao extends MongoRepository<State, String> {

	Optional<State> findByStateName(StateName name);

}