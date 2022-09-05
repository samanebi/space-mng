package com.park.spacemng.service.space.owner.impl;

import com.park.spacemng.service.redis.impl.OnlineUserRedisDao;
import com.park.spacemng.service.space.owner.OnlineOwnerOperationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OnlineOwnerOperationServiceImpl implements OnlineOwnerOperationService {

	private final OnlineUserRedisDao dao;

	@Override
	public boolean isOnline(String ownerId) {
		log.info("getting to know that user is online {}", ownerId);
		return dao.exists(ownerId);
	}

}