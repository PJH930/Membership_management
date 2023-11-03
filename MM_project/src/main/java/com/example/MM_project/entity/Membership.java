package com.example.MM_project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor // 기본생성자 추가
@AllArgsConstructor
@ToString
@Getter
@Entity
public class Membership {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String userClass;
    @Column
    private String name;
    @Column
    private String date;
    @Column
    private String tel;
    @Column
    private String email;
    @Column
    private String area;
    @Column
    private String memo;
    @Column
    private String file;






}
