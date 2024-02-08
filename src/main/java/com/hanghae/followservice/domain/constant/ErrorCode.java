package com.hanghae.followservice.domain.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not founded"),
    USER_CANNOT_FOLLOW_SELF(HttpStatus.BAD_REQUEST, "User cannot follow themselves");

    private final HttpStatus status;
    private final String message;
}
