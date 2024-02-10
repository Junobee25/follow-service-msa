package com.hanghae.followservice.domain.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AlarmType {

    NEW_FOLLOW_TO_USER("new follow");

    private final String alarmType;
}
