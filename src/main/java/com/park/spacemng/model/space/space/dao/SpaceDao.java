package com.park.spacemng.model.space.space.dao;

import java.util.List;

import com.park.spacemng.model.constants.District;
import com.park.spacemng.model.constants.StateName;
import com.park.spacemng.model.constants.Town;
import com.park.spacemng.model.space.space.Space;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SpaceDao extends MongoRepository<Space, String> {

	@Query("{ 'stateName' : { '$in' : ?0 }, 'district' : { '$in' : ?1 }, 'town' : { '$in' : ?2 } }")
	List<Space> findAllByStateNameAndDistrictAndTown(List<StateName> stateNames, List<District> districts, List<Town> towns);

	List<Space> findAllByBatchIdAndStatus(String batchId, Space.Status status);

	List<Space> findAllByBatchId(String batchId);

	List<Space> findAllByPositionIsNear(Point point, Distance distance);

	List<Space> findByOwnerId(String id);

	int countAllByBatchIdAndStatus(String batchId, Space.Status status);

}