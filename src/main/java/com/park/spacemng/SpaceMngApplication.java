package com.park.spacemng;

import com.park.spacemng.config.LocationSelectionProperties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(LocationSelectionProperties.class)
public class SpaceMngApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpaceMngApplication.class, args);
	}

}