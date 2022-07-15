package com.park.spacemng.service.user.online.impl;

import java.util.Date;

import com.park.spacemng.config.OnlineUserProperties;
import com.park.spacemng.service.redis.impl.OnlineUserRedisDao;
import com.park.spacemng.service.user.online.OnlineUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OnlineUserServiceImpl implements OnlineUserService {

	private final OnlineUserRedisDao dao;

	private final OnlineUserProperties properties;

	@Override
	public void processHeartBeat(String userId) {
		dao.setOrUpdate(userId, new Date().getTime());
	}

}