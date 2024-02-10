package com.hanghae.followservice.external.controller;

import com.hanghae.followservice.external.service.ExternalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/follow-service")
public class ExternalController {

    private final ExternalService externalService;

    @GetMapping("/find-following-user")
    public List<Long> getFollowingUser(@RequestParam(value = "fromUserId", required = false) Long fromUserId) {
        return externalService.getFollowingUsers(fromUserId);
    }
}
