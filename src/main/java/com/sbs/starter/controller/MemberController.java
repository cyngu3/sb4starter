package com.sbs.starter.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sbs.starter.dto.Member;
import com.sbs.starter.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j // 이걸 해야 log.info(..) 가 가능, 디버깅 용도
public class MemberController {
	@Autowired //@Autowired  ///:@Service를 만나서 진행:
	MemberService memberService;

	@RequestMapping("/member/login")
	public String showLogin() {
		return "member/login";
	}

	@RequestMapping("/member/doLogout")
	public String doLogout(HttpSession session) {
		session.removeAttribute("loginedMemberId");
		return "redirect:/";	//=> main.jsp
	}

	@RequestMapping("/member/doLogin")
	public String doLogin(@RequestParam Map<String, Object> param, Model model, HttpSession session) {
		Member matchedMember = memberService.getMatchedOne((String) param.get("loginId"),
				(String) param.get("loginPw"));

		if (matchedMember == null) {
			model.addAttribute("alertMsg", "일치하는 회원이 존재하지 않습니다.");
			model.addAttribute("historyBack", true);
			return "common/redirect";
		}
		// 해당 로그인 회원의 번호로 DB에서 회원의 정체 정보를 불러오는데 (거의 모든 액션에서 이것을 반복해서 해줘야 하는 것을 여기서 처리)
		session.setAttribute("loginedMemberId", matchedMember.getId()); ////로그인 했는지 체크하고
		
		model.addAttribute("alertMsg", " 로그인 되었습니다.");
		model.addAttribute("redirectUrl", "/");
		log.info("cy_ck id="+matchedMember.getId()+", loginId="+(String)param.get("loginId"));
		return "common/redirect";
	}

	@RequestMapping("/member/join")
	public String showJoin() {
		return "member/join";
	}

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

