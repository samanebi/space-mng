package com.park.spacemng.config;

import com.park.spacemng.model.constants.LocationSelectionType;
import lombok.Getter;
import lombok.Setter;

import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties(prefix = "location.selection")
public class LocationSelectionProperties {

	private LocationSelectionType defaultType;

}