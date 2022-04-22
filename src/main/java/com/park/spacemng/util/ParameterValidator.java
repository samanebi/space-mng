package com.park.spacemng.util;

import java.util.Objects;

import com.park.spacemng.config.ParameterValidationMessageProperties;
import com.park.spacemng.exception.GeneralException;
import com.park.spacemng.exception.ParameterValidationException;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public final class ParameterValidator {

	private final ParameterValidationMessageProperties messages;

	public void requireParameterNotNullOrBlank(String argument) throws GeneralException {
		requireParameterNotNull(argument);
		ExceptionGenerator.generateIfFalse(Strings.isNotBlank(argument),
				ParameterValidationException.class, messages.getBlankParameter());
	}

	public void requireParameterNotNull(Object argument) throws GeneralException {
		ExceptionGenerator.generateIfFalse(Objects.isNull(argument), ParameterValidationException.class,
				messages.getNullParameter());
	}

	public void requireParameterNotEqualTo(Object argument, Object target) throws GeneralException {
		requireParameterNotNull(argument);
		ExceptionGenerator.generateIfFalse(Objects.equals(argument, target),
				ParameterValidationException.class, messages.getEqualParameter().formatted(argument, target));
	}

}