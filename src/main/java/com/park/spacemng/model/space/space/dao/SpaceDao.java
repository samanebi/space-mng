package com.park.spacemng.model.space.space.dao;

import java.util.List;

import com.park.spacemng.model.space.space.Space;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpaceDao extends MongoRepository<Space, String> {

	List<Space> findAllByBatchIdAndStatus(String batchId, Space.Status status);

	List<Space> findAllByBatchId(String batchId);

}