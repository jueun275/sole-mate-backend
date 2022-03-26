package com.solemate.endpoint.user.dto;

import com.solemate.common.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserResponseDto {
    private Long userCd;
    private String userId;
    private String userName;
    private String userPic;
    private String userEx;

    public UserResponseDto(User user){
        this.userCd = user.getUserCd();
        this.userId = user.getUserId();
        this.userName = user.getUserName();
        this.userPic = user.getUserPic();
        this.userEx = user.getUserEx();
    }
}
