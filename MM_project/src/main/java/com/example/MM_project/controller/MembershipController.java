package com.example.MM_project.controller;
import com.example.MM_project.dto.MembershipForm;
import com.example.MM_project.entity.Membership;
import com.example.MM_project.repository.MembershipRepository;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Slf4j // 로깅 사용
@Controller // 컨트롤러다
public class MembershipController {
    @Autowired
    private MembershipRepository membershipRepository;

    @GetMapping("/index")
    public String welcome(Model model){
        return "/membership/welcome";
    }
    @GetMapping("/membership/addUser")
    public String addUser(){
        return "membership/addUser";
    }

    @GetMapping("/membership/create")
    public String createUser(MembershipForm form){
        log.info(form.toString());
        // 엔티티변환
        Membership membership = form.toEntity();
        log.info(membership.toString());
        // 엔티티 디비에 저장
        Membership saved = membershipRepository.save(membership);
        log.info(saved.toString());

        return "redirect:/membership/" + saved.getId();
    }

    @GetMapping("/membership/{tel}")
    public String searchUser(){

        return "";
    }

    @GetMapping("/membership/{id}")
    public String showUser(@PathVariable Long id, Model model){
        log.info("id = " + id);
        // id 조회
        Membership membershipEntity = membershipRepository.findById(id).orElse(null);
        // 데이터등록
        model.addAttribute("membership", membershipEntity);
        // 뷰 반영
        return "/membership/show";
    }

    @GetMapping("/membership/allUser")
    public String allUser(Model model){
        ArrayList<Membership> membershipEntityList = membershipRepository.findAll();

        model.addAttribute("membershipList", membershipEntityList);

        return "/membership/allUser";
    }

}
