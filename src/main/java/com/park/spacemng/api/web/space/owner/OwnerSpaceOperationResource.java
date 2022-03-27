package com.park.spacemng.api.web.space.owner;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.park.spacemng.api.web.space.owner.mapper.OwnerSpaceOperationResourceMapper;
import com.park.spacemng.model.constants.ProcessStatus;
import com.park.spacemng.model.request.SpaceGenerationRequest;
import com.park.spacemng.model.request.SpaceResolutionRequest;
import com.park.spacemng.model.request.SpaceUpdateRequest;
import com.park.spacemng.model.response.GeneralResponse;
import com.park.spacemng.model.response.SpaceRetrievalResponse;
import com.park.spacemng.service.space.owner.OwnerSpaceOperationService;
import com.park.spacemng.service.space.owner.model.SpaceRequestsRetrievalResult;
import com.park.spacemng.util.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/spaces")
public class OwnerSpaceOperationResource {

	private final OwnerSpaceOperationService service;

	private final OwnerSpaceOperationResourceMapper mapper;

	@PostMapping(value = "/generate",
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GeneralResponse> generateSpaces(@NotNull @RequestBody SpaceGenerationRequest request,
			@NotBlank @RequestHeader(Constants.HEADER_USER_ID) String userId) {
		service.generateSpaces(mapper.toSpaceGenerationModel(request, userId));
		return new ResponseEntity<>(new GeneralResponse(ProcessStatus.SUCCESS), HttpStatus.OK);
	}

	@PostMapping(value = "/space/update",
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GeneralResponse> updateSpace(@NotNull @RequestBody SpaceUpdateRequest request,
			@NotBlank @RequestHeader(Constants.HEADER_USER_ID) String userId) {
		service.updateSpace(mapper.toSpaceUpdateModel(request, userId));
		return new ResponseEntity<>(new GeneralResponse(ProcessStatus.SUCCESS), HttpStatus.OK);
	}

	@GetMapping(value = "/space/requests/{batchId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SpaceRetrievalResponse> getSpaceRequests(
			@NotBlank @RequestHeader(Constants.HEADER_USER_ID) String userId,
			@NotBlank @PathVariable String batchId) {
		SpaceRequestsRetrievalResult result = service.getSpaceRequests
				(mapper.toSpaceRequestsRetrievalModel(userId, batchId));
		return new ResponseEntity<>(mapper.toSpaceRetrievalResponse(result), HttpStatus.OK);
	}

	@PostMapping(value = "/space/requests/resolve",
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GeneralResponse> resolveSpaceRequests(
			@NotNull @RequestBody SpaceResolutionRequest request,
			@NotBlank @RequestHeader(Constants.HEADER_USER_ID) String userId,
			@NotBlank @PathVariable String batchId) {
		service.resolveSpaceRequests(mapper
				.toSpaceRequestsResolutionModel(request, userId, batchId));
		return new ResponseEntity<>(new GeneralResponse(ProcessStatus.SUCCESS), HttpStatus.OK);
	}

}