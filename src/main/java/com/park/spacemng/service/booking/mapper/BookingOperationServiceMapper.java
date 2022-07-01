package com.park.spacemng.service.booking.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.park.spacemng.model.booking.BookingRequest;
import com.park.spacemng.model.constants.RequestResolution;
import com.park.spacemng.model.user.driver.Driver;
import com.park.spacemng.model.user.owner.Owner;
import com.park.spacemng.service.booking.model.BookingRequestDetails;
import com.park.spacemng.service.booking.model.BookingRequestDriverInfo;
import com.park.spacemng.service.booking.model.BookingRequestOwnerInfo;
import com.park.spacemng.service.booking.model.BookingRequestsRetrievalResult;
import com.park.spacemng.service.user.driver.model.DriverInfo;
import com.park.spacemng.service.user.owner.model.OwnerInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ValueMapping;

@Mapper(componentModel = "spring")
public interface BookingOperationServiceMapper {

	Driver toDriver(DriverInfo driverInfo);

	@Mapping(target = "id", source = "ownerId")
	Owner toOwner(OwnerInfo ownerInfo);

	BookingRequestOwnerInfo toBookingRequestOwnerInfo(Owner owner);

	BookingRequestDriverInfo toBookingRequestDriverInfo(Driver driver);

	BookingRequestDetails toBookingRequestDetails(BookingRequest request);

	BookingRequest toBookingRequest(BookingRequestDetails details);

	default BookingRequestsRetrievalResult toBookingRequestsRetrievalResult(List<BookingRequest> requests) {
		BookingRequestsRetrievalResult requestsRetrievalResult = new BookingRequestsRetrievalResult();
		requestsRetrievalResult.setRequests(toBookingRequestsDetailsList(requests));
		return requestsRetrievalResult;
	}

	List<BookingRequestDetails> toBookingRequestsDetailsList(List<BookingRequest> requests);

	@ValueMapping(source = "APPROVE", target = "ACCEPTED")
	@ValueMapping(source = "REJECT", target = "REJECTED")
	BookingRequest.Status toStatus(RequestResolution resolution);


	default Map<String, RequestResolution> toToBookingRequestList(List<BookingRequestDetails> bookingRequestDetails) {
		Map<String, RequestResolution> result = new HashMap<>();
		bookingRequestDetails.forEach(request -> result.put(request.getTrackingCode(),
				request.getResolution()));
		return result;
	}

}