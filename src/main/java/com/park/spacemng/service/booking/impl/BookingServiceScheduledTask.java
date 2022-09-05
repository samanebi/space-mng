package com.park.spacemng.service.booking.impl;

import com.park.spacemng.model.booking.BookingRequest;
import com.park.spacemng.model.booking.dao.BookingRequestDao;
import com.park.spacemng.service.booking.BookingOperationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class BookingServiceScheduledTask {

    private final BookingRequestDao bookingRequestDao;

    private final BookingOperationService bookingOperationService;

    @Scheduled(fixedDelay = 900000)
    public void processDanglingBookingRequests() {
        log.info("going to expire dangling booking requests.");
        List<BookingRequest> bookingRequests = bookingRequestDao
                .findByStatusAndCreationDateAfter(BookingRequest.Status.INITIATED,
                        new Date().getTime() - 900000);
        bookingRequests.forEach(bookingOperationService::expire);
        bookingRequestDao.saveAll(bookingRequests);
    }

}
