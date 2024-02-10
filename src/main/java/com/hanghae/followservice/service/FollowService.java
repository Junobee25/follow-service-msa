package com.hanghae.followservice.service;

import com.hanghae.followservice.external.client.AlarmServiceClient;
import com.hanghae.followservice.external.client.UserServiceClient;
import com.hanghae.followservice.domain.constant.ErrorCode;
import com.hanghae.followservice.domain.entity.Follow;
import com.hanghae.followservice.domain.repository.FollowRepository;
import com.hanghae.followservice.exception.FollowServiceApplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final UserServiceClient userServiceClient;
    private final AlarmServiceClient alarmServiceClient;

    public void follow(Long toUser, HttpHeaders headers) {

        /* Using a feign client */
        String fromUserEmail = userServiceClient.getMyEmail(headers);
        Optional<Long> fromUser = userServiceClient.getUserId(fromUserEmail);

        Optional<Long> checkToUser = userServiceClient.getToUserId(toUser, headers);
        checkToUser.orElseThrow(() -> new FollowServiceApplicationException(ErrorCode.USER_NOT_FOUND));

        if (fromUser.isPresent()) {
            if (fromUser.get().equals(toUser)) {
                throw new FollowServiceApplicationException(ErrorCode.USER_CANNOT_FOLLOW_SELF);
            }
            Optional<Follow> existingFollow = followRepository.findByFromUserAndToUser(fromUser.get(), toUser);

            if (existingFollow.isPresent()) {
                followRepository.delete(existingFollow.get());
            } else {
                followRepository.save(Follow.of(fromUser.get(), toUser));
                alarmServiceClient.saveAlarm(toUser, fromUser.get(), Follow.of(fromUser.get(), toUser).getId(), "NEW_FOLLOW");
            }
        }
    }
}
