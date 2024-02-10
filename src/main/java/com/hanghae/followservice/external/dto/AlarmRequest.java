package com.hanghae.followservice.external.dto;

import com.hanghae.followservice.domain.constant.AlarmType;

public record AlarmRequest(
        Long toUserId,
        Long fromUserId,
        Long targetId,
        AlarmType alarmType
) {

    public static AlarmRequest of(Long toUserId,
                                  Long fromUserId,
                                  Long targetId,
                                  AlarmType alarmType) {
        return new AlarmRequest(toUserId, fromUserId, targetId, alarmType);
    }
}
