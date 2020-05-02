package com.sbs.starter.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.starter.dto.Article;
import com.sbs.starter.service.ArticleService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j	//Lombok: log.info가 가능하게 한다
public class ArticleController {
	@Autowired		//:@Service를 만나서 진행:
	ArticleService articleService;
	
	@RequestMapping("/article/list") ///@ResponseBody일 경우 : 직접 "메인화면입니다"가 나온다.
	public String showList(Model aModel) {	//초기에는 ("") 이었음: 
		List<Article> list = articleService.getList(); 
		aModel.addAttribute("list", list);
		//request.setAttribute("list", list)와 똑같은 표현!!
		return "article/list";
	}
	
	@RequestMapping("/article/add")
	public String showAdd() {	
		return "article/add"; //add(form)이다  
	}
	
	@RequestMapping("/article/doAdd") //=>articleDAO =>articleService 에서 처리한다
	@ResponseBody   //@ResponseBody일 경우 : 직접 "메인화면입니다"가 나온다.
	public String doAdd(@RequestParam Map<String, Object> param) { //String title, String Body
		articleService.add(param);  //long newId = articleService.add(param);
		return "게시물이 추가되었습니다"; //"article/add";   //
	}
}
