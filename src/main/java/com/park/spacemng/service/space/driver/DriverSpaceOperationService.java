package com.park.spacemng.service.space.driver;

import com.park.spacemng.exception.GeneralException;
import com.park.spacemng.service.space.driver.model.DriverSpaceBookingModel;
import com.park.spacemng.service.space.driver.model.DriverSpaceBookingResult;
import com.park.spacemng.service.space.driver.model.NearbyAvailableSpacesRetrievalModel;
import com.park.spacemng.service.space.driver.model.NearbyAvailableSpacesRetrievalResult;

public interface DriverSpaceOperationService {

	NearbyAvailableSpacesRetrievalResult getNearbyAvailableSpaces(NearbyAvailableSpacesRetrievalModel model) throws GeneralException;

	DriverSpaceBookingResult bookSpace(DriverSpaceBookingModel model) throws GeneralException;

}