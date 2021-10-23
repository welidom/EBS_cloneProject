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

	public Object FaqlistCategory() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object FaqreadTitle(int cno, String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object FaqtotalBnoCount(int cno, String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object Faqfadtorite() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object FaqFadtoriteTotalBnoCount(int cno, String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	public void FaqnewQuestion(String category, int cno, String question, String answer) {
		// TODO Auto-generated method stub
		
	}

	public void Faqdelete(int cno, int bno) {
		// TODO Auto-generated method stub
		
	}

	public Object FaqupdateView(int cno, int bno) {
		// TODO Auto-generated method stub
		return null;
	}

	public void Faqupdate(int cno, String category, String question, String answer, int bno) {
		// TODO Auto-generated method stub
		
	}

	public void Faqreadcount(int cno, int bno) {
		// TODO Auto-generated method stub
		
	}
}
