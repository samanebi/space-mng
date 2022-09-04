package com.park.spacemng.exception;

import com.park.spacemng.model.constants.ProcessStatus;

public class InvalidCredentialsException extends GeneralException{

    public InvalidCredentialsException(String message) {
        super(ProcessStatus.INVALID_CREDENTIALS, message);
    }

}
