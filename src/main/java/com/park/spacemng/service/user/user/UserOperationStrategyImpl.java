package com.park.spacemng.service.user.user;

import com.park.spacemng.model.user.constants.UserType;
import com.park.spacemng.model.user.driver.Driver;
import com.park.spacemng.model.user.owner.Owner;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserOperationStrategyImpl implements UserOperationStrategy {

    private final UserOperationService<Driver> driverUserOperationService;

    private final UserOperationService<Owner> ownerUserOperationService;

    @Override
    public UserOperationService get(UserType type) {
        return switch (type){
            case OWNER -> ownerUserOperationService;
            default -> driverUserOperationService;
        };
    }

}
