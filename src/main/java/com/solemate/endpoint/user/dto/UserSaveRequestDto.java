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
    private String userNm;

    public void setPassword(String userPassword){
        this.userPassword = userPassword;
    }

    @Builder
    public UserSaveRequestDto(String userId, String userPassword, String userNm){
        this.userId = userId;
        this.userPassword = userPassword;
        this.userNm = userNm;
    }

    public User toEntity(){
        return User.builder()
                .userId(userId)
                .userPassword(userPassword)
                .userNm(userNm)
                .build();
    }
}
