package com.hanghae.followservice.external.client;

import com.hanghae.followservice.external.dto.AlarmRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="alarm-service")
public interface AlarmServiceClient {

    @PostMapping("/alarm-service/save-alarm")
    void saveAlarm(@RequestBody AlarmRequest alarmRequest);
}
