package com.park.spacemng.service.space.owner.model;

import java.awt.Point;

import lombok.Data;

@Data
public class OwnerSpaceGenerationModel {

	private String batchId;

	private String title;

	private String description;

	private String address;

	private Point location;

	private int capacity;

}