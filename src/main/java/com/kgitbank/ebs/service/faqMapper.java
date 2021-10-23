package com.kgitbank.ebs.service;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.kgitbank.ebs.model.FaqDTO;

@Service
public class faqMapper {

	@Inject
	private SqlSession sqlSession;
	
	private static String namespace = "com.kgitbank.ebs.mapper.faqMapper";

	public List<FaqDTO> faqList(){
		List<FaqDTO> list = sqlSession.selectList(namespace+".faqList");
		return list;
	}
	public List<FaqDTO> faqList(String keyword){
		List<FaqDTO> list = sqlSession.selectList(namespace+".faqListByCategory", keyword);
		return list;
	}
	public Object FaqlistCategory() {
		return null;
	}
	public Object FaqreadTitle(int cno, String keyword) {
		return null;
	}
	public Object FaqtotalBnoCount(int cno, String keyword) {
		return null;
	}
	public Object Faqfavorite() {
		return null;
	}
	public Object FaqFavoriteTotalBnoCount(int cno, String keyword) {
		return null;
	}
}
