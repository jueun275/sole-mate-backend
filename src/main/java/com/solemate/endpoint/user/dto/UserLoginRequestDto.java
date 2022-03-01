package com.solemate.endpoint.user.dto;

import com.solemate.common.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserLoginRequestDto {
    public String userId;
    public String userPassword;

    @Builder
    public UserLoginRequestDto(String userId, String userPassword){
        this.userId = userId;
        this.userPassword = userPassword;
    }

    public User toEntity(){
        return User.builder()
                .userId(userId)
                .userPassword(userPassword)
                .build();
    }
}
