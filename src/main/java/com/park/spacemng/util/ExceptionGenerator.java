package com.park.spacemng.util;

import com.park.spacemng.exception.GeneralException;
import com.park.spacemng.exception.ParameterValidationException;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class ExceptionGenerator {

	public void generateIfFalse(boolean condition, Class<? extends GeneralException> exception, String message)
			throws GeneralException {
		if (!condition) {
			throw new ParameterValidationException(message);
		}
	}

}