package com.park.spacemng.service.user.user;

import com.park.spacemng.model.user.constants.UserType;
import lombok.RequiredArgsConstructor;

public interface UserOperationStrategy {

    abstract UserOperationService get(UserType type);

}
