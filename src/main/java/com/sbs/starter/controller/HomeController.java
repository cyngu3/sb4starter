package com.sbs.starter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sbs.starter.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {
	@Autowired
	MemberService memberService;

	@RequestMapping("/home/main")		  //(public String ShowMain(HttpSession session,Model model)
	public String showMain(Model model) { //if (session.getAttribute("loginedMemberId) != null) {
		return "home/main";				  //로그인 체크: long loginedMemberId= (long)session.getAttribute("loginedMemberId"); } 
	}

	@RequestMapping("/")
	public String showMain2() {		//(HttpSession session)
		return "redirect:/home/main";
	}
}
