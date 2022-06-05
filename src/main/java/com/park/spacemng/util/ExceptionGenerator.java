package com.park.spacemng.util;

import java.util.function.Consumer;

import com.park.spacemng.exception.GeneralException;
import com.park.spacemng.exception.ParameterValidationException;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@UtilityClass
@Slf4j
public final class ExceptionGenerator {

	public void generateIfFalse(boolean condition, String message)
			throws GeneralException {
		if (!condition) {
			throw new ParameterValidationException(message);
		}
	}

	public static <T, E extends Exception> Consumer<T>
	runtimeExceptionWrapper(Consumer<T> consumer, Class<E> clazz) {

		return i -> {
			try {
				consumer.accept(i);
			} catch (Exception ex) {
				try {
					E exCast = clazz.cast(ex);
					log.error(
							"Exception occured : " + exCast.getMessage());
				} catch (ClassCastException ccEx) {
					throw ex;
				}
			}
		};
	}

}