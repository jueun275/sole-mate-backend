package com.solemate.endpoint.user;

import com.solemate.common.domain.User;
import com.solemate.common.repository.UserRepository;
import com.solemate.endpoint.user.dto.UserLoginRequestDto;
import com.solemate.endpoint.user.dto.UserResponseDto;
import com.solemate.endpoint.user.dto.UserSaveRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @AfterEach
    public  void tearDown() throws Exception{
        userRepository.deleteAll();
    }

    private final String testUserId = "testUserID";
    private final String testUserPassword = "testUserPassword";

    @Test
    public void User_회원가입(){
        UserSaveRequestDto requestDto = UserSaveRequestDto.builder()
                .userId(testUserId)
                .userPassword(testUserPassword)
                .userNm("testUser")
                .build();


        String url = "http://localhost:" + port + "/user/join";

        //when
        ResponseEntity<Boolean> responseEntity = restTemplate.postForEntity(url, requestDto, Boolean.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(true);

        List<User> all = userRepository.findAll();
        assertThat(all.get(0).getUserId()).isEqualTo(testUserId);
        assertThat(all.get(0).getUserPassword()).isEqualTo(testUserPassword);
    }

    @Test
    public void User_로그인(){
        //given
        User_회원가입();
        UserLoginRequestDto requestDto = UserLoginRequestDto.builder()
                .userId(testUserId)
                .userPassword(testUserPassword)
                .build();

        String url = "http://localhost:" + port + "/user/login";

        //when
        ResponseEntity<UserResponseDto> responseEntity = restTemplate.postForEntity(url, requestDto, UserResponseDto.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat((responseEntity.getBody()).getUserId()).isEqualTo(testUserId);

    }

}
