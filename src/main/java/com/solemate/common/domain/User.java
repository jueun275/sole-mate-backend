package com.solemate.common.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userCd;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String userPassword;

    @Column(nullable = true)
    private String userLoc;

    @Column(nullable = true)
    private String userPic;

    @Column(nullable = true)
    private String userEx;

    @Column(nullable = true)
    private String userSex;

    @Builder
    public User(String userId,
                String userPassword,
                String userName,
                String userPic)
    {
        this.userId = userId;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userPic = userPic;
    }
}
