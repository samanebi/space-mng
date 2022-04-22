package com.park.spacemng.model.booking.dao;

import java.util.List;

import com.park.spacemng.model.booking.BookingRequest;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRequestDao extends MongoRepository<BookingRequest, String> {

	@Query("{'trackingCode' : {$in : ?0}}")
	List<BookingRequest> findAllByTrackingCodes(List<String> trackingCodes);

	List<BookingRequest> findAllByBatchId(String batchId);

}