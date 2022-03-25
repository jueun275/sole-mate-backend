package com.solemate.endpoint.dog;

import com.solemate.common.domain.Dog;
import com.solemate.common.domain.User;
import com.solemate.common.repository.DogRepository;
import com.solemate.endpoint.dog.dto.DogSaveRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DogControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private DogRepository dogRepository;

    @AfterEach
    public  void tearDown() throws Exception{
        dogRepository.deleteAll();
    }

    @Test
    public void Dog_동록하다(){
        DogSaveRequestDto requestDto = DogSaveRequestDto.builder()
                .dogName("test")
                .dogAge(0)
                .dogBread("dogBread")
                .dogSex("dogSex")
                .dogSize("dogSize")
                .dogPic("/")
                .build();

        String url = "http://localhost:" + port + "/dog";

        //when
        ResponseEntity<Object> responseEntity = restTemplate.postForEntity(url, requestDto, Object.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(1);

        List<Dog> all = dogRepository.findAll();
        assertThat(all.get(0).getDogName()).isEqualTo("test");
    }
}
