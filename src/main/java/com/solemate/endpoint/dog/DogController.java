package com.solemate.endpoint.dog;

import com.solemate.endpoint.dog.dto.DogSaveRequestDto;
import com.solemate.endpoint.dog.service.DogService;
import com.solemate.endpoint.user.dto.UserSaveRequestDto;
import com.solemate.endpoint.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("dog")
@RestController
public class DogController {

    private final DogService dogService;

    @PostMapping("")
    public Long save(@RequestBody DogSaveRequestDto requestDto){
        return dogService.save(requestDto);
    }

}
