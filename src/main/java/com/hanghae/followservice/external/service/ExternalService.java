package com.hanghae.followservice.external.service;

import com.hanghae.followservice.domain.entity.Follow;
import com.hanghae.followservice.domain.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExternalService {

    private final FollowRepository followRepository;

    public List<Long> getFollowingUsers(Long fromUserId) {
        return followRepository.findByFromUser(fromUserId).stream()
                .map(Follow::getToUser)
                .toList();
    }
}
