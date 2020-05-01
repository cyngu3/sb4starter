package com.sbs.starter.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sbs.starter.dto.ArticleDTO;
import com.sbs.starter.service.ArticleService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j	//Lombok: log.info가 가능하게 한다
public class ArticleController {
	@Autowired		//:@Service를 만나서 진행:
	ArticleService articleService;
	
	@RequestMapping("/article/list")
	//@ResponseBody ////직접 "메인화면입니다"가 나온다:
	public String showList(Model aModel) {	//처음에는 ("") 이었음: 
		List<ArticleDTO> list = articleService.getList(); 
		//log.info("list : "+ list); //확인용으로 쓰고서 닫음
		aModel.addAttribute("list", list);
		//request.setAttribute("list", list)와 똑같은 표현!!
		return "article/list";   ////@ResponseBody일 경우 : 직접 "메인화면입니다"가 나온다.
	}
}
