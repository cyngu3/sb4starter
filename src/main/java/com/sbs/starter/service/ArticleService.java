package com.sbs.starter.service;

import java.util.List;
import java.util.Map;

import com.sbs.starter.dto.Article;

public interface ArticleService {
	public List<Article> getList();
	
	public long add(Map<String, Object> param);
	//public long add(Map<String, Object> param);

	public int getTotalCount(); //요청 시작은 :articleController에서 요청한다= :articleService.add(param);
	//CoSIDaMb//C-S-SI-DAO-myBatis//실행은 implement에서 실시하고   //이것이 articleDao.에도 만들어져 있어야 한다.=> myBatis

	public Article getOne(long id);

	public void delete(long id);

	public void modify(Map<String, Object> param);

	public void hitUp(long id);
}
