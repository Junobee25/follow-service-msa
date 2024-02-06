package com.hanghae.followservice.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "from_user_id", nullable = false)
    private String fromUser;

    @Column(name = "to_user_id", nullable = false)
    private String toUser;

    public static Follow of(String fromUser, String toUser){
        Follow follow = new Follow();
        follow.setFromUser(fromUser);
        follow.setToUser(toUser);
        return follow;
    }
}
