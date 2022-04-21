package com.park.spacemng.service.trackincode.impl;

import java.util.Random;

import com.park.spacemng.config.TrackingCodeProperties;
import com.park.spacemng.service.trackincode.TrackingCodeOperationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrackingCodeOperationServiceImpl implements TrackingCodeOperationService {

	private static final String NUMBERS = "0123456789";

	private static final Random RANDOM = new Random();

	private final TrackingCodeProperties properties;

	@Override
	public String generate() {
		StringBuilder builder = new StringBuilder();
		for (int counter = 0; counter < properties.getLength(); counter++) {
			builder.append(NUMBERS.charAt(RANDOM.nextInt(NUMBERS.length())));
		}
		return builder.toString();
	}

}