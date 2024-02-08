package com.hanghae.followservice.service;

import com.hanghae.followservice.client.UserServiceClient;
import com.hanghae.followservice.domain.constant.ErrorCode;
import com.hanghae.followservice.domain.entity.Follow;
import com.hanghae.followservice.domain.repository.FollowRepository;
import com.hanghae.followservice.exception.FollowServiceApplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final UserServiceClient userServiceClient;

    public void follow(Long toUser, HttpHeaders headers) {

        /* Using a feign client */
        Optional<Long> fromUserIdOptional = userServiceClient.getFromUserId(headers);
        Long fromUser = fromUserIdOptional.orElseThrow(() -> null);

        if (fromUser.equals(toUser)) {
            throw new FollowServiceApplicationException(ErrorCode.USER_CANNOT_FOLLOW_SELF);
        }

        Optional<Long> checkToUser = userServiceClient.getToUserId(toUser, headers);
        checkToUser.orElseThrow(() -> new FollowServiceApplicationException(ErrorCode.USER_NOT_FOUND));

        Optional<Follow> existingFollow = followRepository.findByFromUserAndToUser(fromUser, toUser);
        existingFollow.ifPresentOrElse(followRepository::delete,
                () -> followRepository.save(Follow.of(fromUser, toUser))
        );
    }
}
