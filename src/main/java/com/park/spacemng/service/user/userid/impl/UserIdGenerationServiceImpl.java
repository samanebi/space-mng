package com.park.spacemng.service.user.userid.impl;

import java.util.Random;

import com.park.spacemng.config.UserIdProperties;
import com.park.spacemng.service.user.userid.UserIdGenerationService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserIdGenerationServiceImpl implements UserIdGenerationService {

	private static final String NUMBERS = "0123456789";

	private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	private static final Random RANDOM = new Random();

	private final UserIdProperties properties;

	@Override
	public String generate() {
		StringBuilder builder = new StringBuilder();
		for (int counter = 0; counter < properties.getLength(); counter++) {
			if (RANDOM.nextBoolean()) {
				builder.append(NUMBERS.charAt(RANDOM.nextInt(NUMBERS.length())));
			} else {
				builder.append(NUMBERS.charAt(RANDOM.nextInt(ALPHABET.length())));
			}
		}
		return builder.toString();
	}

}