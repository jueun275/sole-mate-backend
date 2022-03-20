package com.solemate.endpoint.openapi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateFormatTest {

    @Test
    public void DateFormat_확인하기(){
        LocalDate now = LocalDate.now(); // 포맷 정의
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd"); // 포맷 적용
        String formatedNow = now.format(formatter); // 결과 출력
        System.out.println(formatedNow); // 20211202
    }

    @Test
    public void TimeFormat_확인하기(){
        LocalTime now = LocalTime.now();
        LocalTime baseTime = now.minusMinutes(40);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm"); // 포맷 적용
        String time = baseTime.format(formatter);
        System.out.println(time);
    }
}
