package com.sbs.starter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ArticleController {
	@RequestMapping("/article/list")
	//@ResponseBody ////직접 "메인화면입니다"가 나온다:
	public String showList() {
		return "article/list";   ////@ResponseBody일 경우 : 직접 "메인화면입니다"가 나온다.
	}
}
