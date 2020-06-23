package com.orionmiz.guildweb.controller;

import com.orionmiz.guildweb.domain.Member;
import com.orionmiz.guildweb.persistence.MemberRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Log
@Controller
public class LoginController {

    @Autowired
    MemberRepository repo;

    @GetMapping("/login")
    public void login() {

    }

    @GetMapping("/signup")
    public void signUp() {

    }

    @Transactional
    @PostMapping("/signup")
    public String signUpPost(@ModelAttribute("member") Member member, RedirectAttributes rttr) {

        Optional<Member> optionalMember = repo.findById(member.getUsername());

        if (optionalMember.isPresent()) {
            rttr.addFlashAttribute("state", "failed");
        } else {
            repo.save(member);
            rttr.addFlashAttribute("state", "success");
        }

        return "redirect:/login";
    }
}
