package com.park.spacemng.service.booking.mapper;

import java.util.List;

import com.park.spacemng.model.booking.BookingRequest;
import com.park.spacemng.model.constants.RequestResolution;
import com.park.spacemng.model.user.Driver;
import com.park.spacemng.model.user.Owner;
import com.park.spacemng.service.booking.model.BookingRequestDetails;
import com.park.spacemng.service.booking.model.BookingRequestDriverInfo;
import com.park.spacemng.service.booking.model.BookingRequestOwnerInfo;
import com.park.spacemng.service.booking.model.BookingRequestsRetrievalResult;
import com.park.spacemng.service.user.driver.model.DriverInfo;
import com.park.spacemng.service.user.owner.model.OwnerInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookingOperationServiceMapper {

	Driver toDriver(DriverInfo driverInfo);

	Owner toOwner(OwnerInfo ownerInfo);

	BookingRequestOwnerInfo toBookingRequestOwnerInfo(Owner owner);

	BookingRequestDriverInfo toBookingRequestDriverInfo(Driver driver);

	BookingRequestDetails toBookingRequestDetails(BookingRequest request);

	BookingRequestsRetrievalResult toBookingRequestsRetrievalResult(List<BookingRequest> requests);

	@Mapping(source = "APPROVE", target = "ACCEPTED")
	@Mapping(source = "REJECT", target = "REJECTED")
	BookingRequest.Status toStatus(RequestResolution resolution);

	List<BookingRequest> toToBookingRequestList(List<BookingRequestDetails> bookingRequestDetails);

}