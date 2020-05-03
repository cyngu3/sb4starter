package com.sbs.starter.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sbs.starter.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MemberController {
	@Autowired  ///:@Service를 만나서 진행:
	MemberService memberService;
	
	@RequestMapping("/member/join")
	public String showJoin() {
		return "member/join";
	}
	
	//@RequestMapping("/member/doJoin") 	//@ResponseBody
	//public String doJoin(@RequestParam Map<String,Object> param, Model model) {
		//로그인ID의 중복 체크
	//	Map<String, Object> checkLoginIdDupRs = memberService.checkLoginIdDup((String) param.get("loginId"));
		
	//	if (((String) checkLoginIdDupRs.get("resultCode")).startsWith("F-")) {
	//		model.addAttribute("alertMsg", checkLoginIdDupRs.get("msg"));
	//		model.addAttribute("historyBack", true);
	//		return "common/redirect";
	//	}
	//	return "common/redirect";
	//}
	//아래는 최종 판:cy200504:
	@RequestMapping("/member/doJoin")
	public String doJoin(@RequestParam Map<String, Object> param, Model model) {
		// 로그인 ID의 중복성 체크
		Map<String, Object> checkLoginIdDupRs = memberService.checkLoginIdDup((String) param.get("loginId"));

		if (((String) checkLoginIdDupRs.get("resultCode")).startsWith("F-")) {
			model.addAttribute("alertMsg", checkLoginIdDupRs.get("msg"));
			model.addAttribute("historyBack", true);
			return "common/redirect";
		}

		Map<String, Object> joinRs = memberService.join(param);

		if (((String) joinRs.get("resultCode")).startsWith("F-")) {
			model.addAttribute("alertMsg", joinRs.get("msg"));
			model.addAttribute("historyBack", true);
			return "common/redirect";
		}

		model.addAttribute("alertMsg", joinRs.get("msg"));
		model.addAttribute("redirectUrl", "/member/login");

		return "common/redirect";
	}
}
