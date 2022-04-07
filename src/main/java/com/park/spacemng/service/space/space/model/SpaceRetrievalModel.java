package com.park.spacemng.service.space.space.model;

import com.park.spacemng.model.space.Space;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SpaceRetrievalModel {

	private String batchId;

	private Space.Status status;

}