package com.hanghae.followservice.external.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@FeignClient(name="user-service")
public interface UserServiceClient {

    @GetMapping("/user-service/users/email")
    String getMyEmail(@RequestHeader HttpHeaders headers);

    @GetMapping("/user-service/users/user-id")
    Optional<Long> getUserId(@RequestParam("email") String email);

    @GetMapping("/user-service/users/from-user-id")
    Optional<Long> getFromUserId(@RequestHeader HttpHeaders headers);

    @GetMapping("/user-service/users/{toUser}")
    Optional<Long> getToUserId(@PathVariable Long toUser, @RequestHeader HttpHeaders headers);
}
