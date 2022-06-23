package com.park.spacemng.model.space.space.dao;

import java.util.List;

import com.park.spacemng.model.constants.District;
import com.park.spacemng.model.constants.StateName;
import com.park.spacemng.model.constants.Town;
import com.park.spacemng.model.space.space.Space;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SpaceDao extends MongoRepository<Space, String> {

	@Query("{ 'stateName' : { '$in' : ?0 }, 'district' : { '$in' : ?1 }, 'town' : { '$in' : ?2 } }")
	List<Space> findAllByStateNameAndDistrictAndTown(List<StateName> stateNames, List<District> districts, List<Town> towns);

	List<Space> findAllByBatchIdAndStatus(String batchId, Space.Status status);

	List<Space> findAllByBatchId(String batchId);

	@Query("{ 'location.position.X' : { '$gte' : ?0, 'lte': ?1 } }")
	List<Space> findAllByPointX(Double start, Double end);

	@Query("{ 'location.position.Y' : { '$gte' : ?0, 'lte': ?1 } }")
	List<Space> findAllByPointY(Double start, Double end);

}