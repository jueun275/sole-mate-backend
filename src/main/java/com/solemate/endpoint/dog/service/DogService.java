package com.solemate.endpoint.dog.service;

import com.solemate.common.repository.DogRepository;
import com.solemate.endpoint.dog.dto.DogSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DogService {
    private final DogRepository dogRepository;

    public Long save(DogSaveRequestDto requestDto){
        return dogRepository.save(requestDto.toEntity()).getDogCd();
    }

}
