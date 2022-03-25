package com.solemate.common.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dogCd;

    @Column(nullable = false)
    private String dogName;

    @Column(nullable = false)
    private int dogAge;

    @Column(nullable = true)
    private String dogPic;

    @Column(nullable = true)
    private String dogBread;

    @Column(nullable = true)
    private String dogSize;

    @Column(nullable = true)
    private String dogSex;

    @Builder
    private Dog(String dogName,
                int dogAge,
                String dogBread,
                String dogSex,
                String dogSize)
    {
        this.dogName = dogName;
        this.dogAge = dogAge;
        this.dogBread = dogBread;
        this.dogSex = dogSex;
        this.dogSize = dogSize;
    }
}
