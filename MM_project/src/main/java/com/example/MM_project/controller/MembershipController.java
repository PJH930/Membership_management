package com.example.MM_project.controller;
import com.example.MM_project.dto.MembershipForm;
import com.example.MM_project.entity.Membership;
import com.example.MM_project.repository.MembershipRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

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
        return "/membership/addUser";
    }
    @GetMapping("/membership/YesNo")
    public String YesNo(){
        return "/membership/YesNo";
    }

    @GetMapping("/membership/{id}/edit")
    public String editUser(@PathVariable Long id, Model model){
        // 수정할데이터 가져오깅
        Membership membershipEntity = membershipRepository.findById(id).orElse(null);
        // 모델에 데이터 등록하기
        model.addAttribute("membership", membershipEntity);

        // 뷰페이지 수정하기
        return "/membership/edit";
    }

    @GetMapping("/membership/update")
    public String update(MembershipForm form){
        // dto 엔티티로 변환
        Membership membershipEntity = form.toEntity();
        log.info(membershipEntity.toString());
        // 엔티티 디비에 저장
        Membership target = membershipRepository.findById(membershipEntity.getId()).orElse(null); // 기존데이터 가져오기
        if (target != null){
            membershipRepository.save(membershipEntity);
        } // 엔티티를 디비에 저장(갱신)

        // 수정결과 페이지로 리다이렉트
        return "redirect:/membership/" + membershipEntity.getId();
    }

    @GetMapping("/membership/{id}/delete")
    public String delete(@PathVariable Long id){
        log.info("삭쪠요청");
        // 삭제대상 가져오기
        Membership target = membershipRepository.findById(id).orElse(null);
        log.info(target.toString());
        // 대상 엔티티 삭제
        if (target != null) {
            // JavaScript를 사용하여 사용자에게 삭제 여부를 확인
            return "redirect:/membership/confirmDelete/" + id;
        } else {
            // 삭제할 대상이 없는 경우 처리
            return "redirect:/membership/allUser";
        }
    }
    @GetMapping("/membership/confirmDelete/{id}")
    public String confirmDelete(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        return "/membership/confirmDelete";
    }
    @GetMapping("/membership/deleteConfirmed/{id}")
    public String deleteConfirmed(@PathVariable Long id) {
        log.info("삭제 확인 요청");
        // 대상 엔티티 삭제
        Membership target = membershipRepository.findById(id).orElse(null);
        if (target != null) {
            membershipRepository.delete(target);
        }
        // 결과 페이지 리다이렉트
        return "redirect:/membership/allUser";
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

    @GetMapping("/membership/search")
    public String searchUser(@PathVariable Long id, Model model){
        log.info("tel = " + id);
        Membership membershipEntity = membershipRepository.findById(id).orElse(null);
        model.addAttribute("membership", membershipEntity);
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
