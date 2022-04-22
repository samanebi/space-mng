package com.park.spacemng.service.space.owner.model;

import java.util.List;

import com.park.spacemng.model.space.space.Space;
import lombok.Data;

@Data
public class OwnerSpaceRetrievalResult {

	private List<Space> spaces;

}