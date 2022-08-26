package com.park.spacemng.model.user.owner.dao;

import com.park.spacemng.model.user.owner.Owner;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerDao extends MongoRepository<Owner, String> {

    Optional<Owner> findByCellNumber(String cellNumber);

}