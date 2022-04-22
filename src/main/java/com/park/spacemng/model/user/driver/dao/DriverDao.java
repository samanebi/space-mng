package com.park.spacemng.model.user.driver.dao;

import com.park.spacemng.model.user.driver.Driver;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverDao extends MongoRepository<Driver, String> {
}