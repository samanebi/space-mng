package com.park.spacemng.config;

import com.park.spacemng.model.constants.LocationSelectionType;
import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "location.selection")
public class LocationSelectionProperties {

	private LocationSelectionType defaultType;

}