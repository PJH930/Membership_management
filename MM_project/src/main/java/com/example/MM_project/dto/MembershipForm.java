package com.example.MM_project.dto;

import com.example.MM_project.entity.Membership;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@AllArgsConstructor // 생성자 자동추가
@ToString // toString 간소화
public class MembershipForm {
    private Long id;

    private String userClass;
    private String name;
    private String date;
    private String tel;
    private String email;
    private String area;
    private String memo;
    private String file;


    public Membership toEntity() {
        return new Membership(id, userClass, name, date, tel, email, area, memo, file);
    }



}

