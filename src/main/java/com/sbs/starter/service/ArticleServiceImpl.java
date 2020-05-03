package com.sbs.starter.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbs.starter.controller.ArticleController;
import com.sbs.starter.dao.ArticleDao;
import com.sbs.starter.dto.Article;
//import com.sbs.starter.util.CUtil;
import com.sbs.starter.util.CUtil;

import lombok.extern.slf4j.Slf4j;

@Service
//@Slf4j 
public class ArticleServiceImpl implements ArticleService {
	@Autowired	//DAO에서 component에 의한 연결
	ArticleDao articleDao;
	
	//ArticleService는 데이터 관련 사항은 DAO에 넘김: 
	public List<Article> getList() {
		//List<ArticleDTO> list = new ArrayList<>();
		//list.add(article3);
		//list.add(article2);
		//list.add(article1);
		return articleDao.getList();
	}	
	
	@Override
	public long add(Map<String, Object> param) {
		articleDao.add(param);
		//BigInteger bigIntId = (BigInteger)param.get("id");  //하나씩 갖고 올 때 
		//long newId = bigIntId.longValue();
		//return newId;  여기 3줄을 바꾼 것이 아래이다.
		return CUtil.getAsLong(param.get("id"));
	}
	//
	@Override
	public int getTotalCount() {  //이것이 articleService에도 만들어져 있어요 한다.
		return articleDao.getTotalCount();  //이것이 articleDao.에도 만들어져 있어요 한다.
	}
	
	@Override
	public Article getOne(long id) {
		return articleDao.getOne(id);  //ArcleDao에도 잇어야 한다. 그리고 myBatis에도 있어야 한다.(총4군데)
	}
	
	@Override
	public void delete(long id) {
		articleDao.delete(id);
	}

	@Override
	public void modify(Map<String, Object> param) {
		articleDao.modify(param);
	}

	@Override
	public void hitUp(long id) {
		articleDao.hitUp(id);
	}

}