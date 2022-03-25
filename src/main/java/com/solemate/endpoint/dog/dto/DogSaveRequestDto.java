package com.solemate.endpoint.dog.dto;

import com.solemate.common.domain.Dog;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class DogSaveRequestDto {
    private int dogAge;
    private String dogPic;
    private String dogSize;
    private String dogName;
    private String dogBread;
    private String dogSex;

    @Builder
    public DogSaveRequestDto(int dogAge,
                             String dogPic,
                             String dogSize,
                             String dogName,
                             String dogBread,
                             String dogSex)
    {
        this.dogAge = dogAge;
        this.dogName = dogName;
        this.dogBread = dogBread;
        this.dogPic = dogPic;
        this.dogSex = dogSex;
        this.dogSize = dogSize;
    }

    public Dog toEntity(){
        return Dog.builder()
                .dogName(dogName)
                .dogAge(dogAge)
                .dogBread(dogBread)
                .dogSex(dogSex)
                .dogSize(dogSize)
                .build();
    }

}
