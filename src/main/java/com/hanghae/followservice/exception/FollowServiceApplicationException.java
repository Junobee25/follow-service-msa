package com.hanghae.followservice.exception;

import com.hanghae.followservice.domain.constant.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FollowServiceApplicationException extends RuntimeException {

    private ErrorCode errorCode;
    private String message;

    public FollowServiceApplicationException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.message = errorCode.getMessage();
    }

    public FollowServiceApplicationException() {

    }

    @Override
    public String getMessage() {
        return (message == null) ? errorCode.getMessage() : String.format("%s. %s", errorCode.getMessage(), message);
    }
}
