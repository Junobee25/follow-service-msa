package com.hanghae.followservice.domain.repository;

import com.hanghae.followservice.domain.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    Optional<Follow> findByFromUser(Long fromUser);
    Optional<Follow> findByFromUserAndToUser(Long fromUser, Long ToUser);
}
