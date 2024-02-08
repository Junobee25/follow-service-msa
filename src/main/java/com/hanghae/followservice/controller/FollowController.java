package com.hanghae.followservice.controller;

import com.hanghae.followservice.dto.request.FollowRequest;
import com.hanghae.followservice.dto.response.Response;
import com.hanghae.followservice.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/follow-service")
public class FollowController {

    private final FollowService followService;

    @PostMapping("/follow")
    public Response<Void> follow(FollowRequest request, @RequestHeader HttpHeaders headers){
        followService.follow(request.toUser(), headers);
        return Response.success(null);
    }
}

