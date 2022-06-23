package com.park.spacemng.service.space.owner.model;

import java.util.List;

import com.park.spacemng.model.space.space.Space;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OwnerSpaceRetrievalResult {

	private List<Space> spaces;

}