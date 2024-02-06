package com.hanghae.followservice.service;

import com.hanghae.followservice.domain.constant.ErrorCode;
import com.hanghae.followservice.domain.entity.Follow;
import com.hanghae.followservice.domain.repository.FollowRepository;
import com.hanghae.followservice.dto.response.Response;
import com.hanghae.followservice.exception.FollowServiceApplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final RestTemplate restTemplate;
    private final FollowRepository followRepository;

    public void follow(String toUser, HttpHeaders headers) {
        String userAccountUrl = "http://127.0.0.1:8000/user-service/users/email";

        HttpEntity<String> entity = new HttpEntity<>(headers);

        // USER-SERVICE API 호출하여 이메일 정보 가져오기 유저서비스의 반환타입
        ResponseEntity<String> userEmailResponse =
                restTemplate.exchange(userAccountUrl, HttpMethod.GET, entity,
                        String.class);

        String fromUser = userEmailResponse.getBody();

        Optional<Follow> existingFollow = followRepository.findByFromUserAndToUser(fromUser, toUser);

        if (existingFollow.isPresent()) {
            followRepository.delete(existingFollow.get());
        } else {
            followRepository.save(Follow.of(fromUser, toUser));
        }
    }
}
