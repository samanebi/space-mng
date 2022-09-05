package com.park.spacemng.util;

import java.util.Objects;

import com.park.spacemng.config.ParameterValidationMessageProperties;
import com.park.spacemng.exception.GeneralException;
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
				"one of string arguments are blank");
	}

	public void requireParameterNotNull(Object argument) throws GeneralException {
		ExceptionGenerator.generateIfFalse(Objects.nonNull(argument),
				"one of arguments is null");
	}

	public void requireParameterNotEqualTo(Object argument, Object target) throws GeneralException {
		requireParameterNotNull(argument);
		ExceptionGenerator.generateIfFalse(!Objects.equals(argument, target),
				"parameter {0} is equal to {1}".formatted(argument, target));
	}

}