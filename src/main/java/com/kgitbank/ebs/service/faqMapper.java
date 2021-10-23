package com.kgitbank.ebs.service;

import java.util.ArrayList;
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

	public void faqreadcount(int num) {
		sqlSession.update("faqReadcount", num);
	}

	public List<FaqDTO> faqList(int cno) {
		List<FaqDTO> list = sqlSession.selectList(namespace+".cnoFaqList", cno);
		return list;
	}

	public List<FaqDTO> faqReadcountList() {
		List<FaqDTO> list = sqlSession.selectList(namespace+".readcountFaqList");
		return list;
	}
}
