package com.solemate.endpoint.user.dto;

import com.solemate.common.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSaveRequestDto {
    private String userId;
    private String userPassword;
    private String userName;

    public void setPassword(String userPassword){
        this.userPassword = userPassword;
    }

    @Builder
    public UserSaveRequestDto(String userId, String userPassword, String userName){
        this.userId = userId;
        this.userPassword = userPassword;
        this.userName = userName;
    }

    public User toEntity(){
        return User.builder()
                .userId(userId)
                .userPassword(userPassword)
                .userName(userName)
                .build();
    }
}
