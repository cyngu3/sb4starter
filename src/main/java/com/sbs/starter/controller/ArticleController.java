package com.sbs.starter.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sbs.starter.dto.Article;
import com.sbs.starter.service.ArticleService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j	//Lombok: log.info가 가능하게 한다
public class ArticleController {
	@Autowired		//:@Service를 만나서 진행:
	ArticleService articleService;
	
	@RequestMapping("/article/list")
	//@ResponseBody ////직접 "메인화면입니다"가 나온다:
	public String showList() {
		List<Article> list = articleService.getList(); 
		
		log.info("list : "+ list); //
		
		return "article/list";   ////@ResponseBody일 경우 : 직접 "메인화면입니다"가 나온다.
	}
}
