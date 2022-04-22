package com.park.spacemng.util;

import com.park.spacemng.exception.GeneralException;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class ExceptionGenerator {

	public void generateIfFalse(boolean condition, Class<? extends GeneralException> exception, String message)
			throws GeneralException {
		if (!condition) {
			throw exception.cast(new Exception(message));
		}
	}

}