package com.solemate.endpoint.user.service;

import com.solemate.common.domain.User;
import com.solemate.common.repository.UserRepository;
import com.solemate.endpoint.user.dto.UserLoginRequestDto;
import com.solemate.endpoint.user.dto.UserResponseDto;
import com.solemate.endpoint.user.dto.UserSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public Boolean save(UserSaveRequestDto requestDto){
        return Optional.of(userRepository.save(requestDto.toEntity())).isPresent();
    }

    public UserResponseDto login(UserLoginRequestDto requestDto){
        User user = Optional.ofNullable(userRepository.findByUserIdAndUserPassword(requestDto.getUserId(), requestDto.getUserPassword()))
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id = " + requestDto.getUserId()));
        return  new UserResponseDto(user);
    }
}
