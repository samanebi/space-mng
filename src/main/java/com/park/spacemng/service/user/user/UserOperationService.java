package com.park.spacemng.service.user.user;

import com.park.spacemng.model.user.User;
import com.park.spacemng.service.user.owner.model.LoginResult;
import com.park.spacemng.service.user.user.model.UserRegistrationModel;

public interface UserOperationService<T extends User> {

    LoginResult login(String username, String password);

    T retrieveUser(String id);

    String registerUser(UserRegistrationModel model);

}
