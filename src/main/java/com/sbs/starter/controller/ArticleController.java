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
	
	@RequestMapping("/article/detail") //아래 list 복사하여 datail 만듬.
	public String showDetail(Model model, long id) {
		Article article = articleService.getOne(id);
		
		articleService.hitUp(id); 
		
		model.addAttribute("article",article);
		return "article/detail";
	}
	
	@RequestMapping("/article/modify") //아래 list 복사하여 datail 만듬.
	public String showModify(Model model, long id) {
		Article article = articleService.getOne(id);
		model.addAttribute("article",article);
		return "article/modify";
	}
	
	@RequestMapping("/article/list") ///@ResponseBody일 경우 : 직접 "메인화면입니다"가 나온다.
	public String showList(Model model) {	//초기에는 ("") 이었음: 
		List<Article> list = articleService.getList();
		int totalCount = articleService.getTotalCount();
		model.addAttribute("list", list); 	//request.setAttribute("list", list)와 똑같은 표현!!
		model.addAttribute("totalCount", totalCount);  //""안의 이름으로 list.jsp에서 표현된다.
		
		return "article/list";
	}
	
	@RequestMapping("/article/add")
	public String showAdd() {	
		return "article/add"; //add(form)이다  
	}
	
	@RequestMapping("/article/doAdd") //=>articleDAO =>articleService 에서 처리한다
	@ResponseBody   //@ResponseBody일 경우 : 직접 "메인화면입니다"가 나온다.
	public String doAdd(@RequestParam Map<String, Object> param) { //String title, String Body
		long newId= articleService.add(param);  //long newId = articleService.add(param);
		
		String msg= newId+ "번 게시물이 추가되었습니다";
		
		StringBuilder sb = new StringBuilder();
		sb.append("alert('"+ msg + "');");
		sb.append("location.replace('./detail?id=" + newId + "');");  //위에 새로detail추가하여 연결
		sb.insert(0, "<script>");
		sb.append("</script>");
		return sb.toString();	//"article/add";
		// sb.append("location.replace('./detail?id="+ newId+"');");
	}
	
	@RequestMapping("/article/doModify") //=>articleDAO =>articleService 에서 처리한다
	@ResponseBody   //@ResponseBody일 경우 : 직접 "메인화면입니다"가 나온다.
	public String doModify(@RequestParam Map<String, Object> param,long id) { //String title, String Body
		articleService.modify(param);  //long newId = articleService.modify(param);
		
		String msg= id+ "번 게시물이 수정되었습니다";
		
		StringBuilder sb = new StringBuilder();
		sb.append("alert('"+ msg + "');");
		sb.append("location.replace('./detail?id=" + id + "');");  //위에 새로detail추가하여 연결
		sb.insert(0, "<script>");
		sb.append("</script>");
		return sb.toString();	//"article/add";
		// sb.append("location.replace('./detail?id="+ newId+"');");
	}
	
	@RequestMapping("/article/doDelete") //=>articleDAO =>articleService 에서 처리한다
	@ResponseBody   //직접 "메인화면입니다"가 나온다.
	public String doDelete(long id) { //String title, String Body
		articleService.delete(id);  //long newId = articleService.add(param);
		
		String msg= id+ "번 게시물이 삭제되었습니다";
		
		StringBuilder sb = new StringBuilder();
		sb.append("alert('"+ msg + "');");
		sb.append("location.replace('./list');");  //위에 새로detail추가하여 연결
		sb.insert(0, "<script>");
		sb.append("</script>");
		return sb.toString();	//"article/add";
		// sb.append("location.replace('./detail?id="+ newId+"');");
	}
}
