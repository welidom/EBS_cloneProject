package com.kgitbank.ebs.service;

import java.util.ArrayList;
import java.util.HashMap;
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

	public List<FaqDTO> faqReadcountList(String keyword) {
		List<FaqDTO> list = sqlSession.selectList(namespace+".faqSearch", keyword);
		for(FaqDTO dto: list) {
			System.out.println(dto.getReadcount());
		}
		return list;
	}

	public Object faqList(int cno, String keyword) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("cno", cno);
		map.put("keyword", keyword);
		List<FaqDTO> list = sqlSession.selectList(namespace+".cnoFaqSearch", map);
		return list;
	}

	public int deleteFaq(int num) {
		int res = sqlSession.delete(namespace+".deleteFaq", num);
		return res;
	}

	public FaqDTO getFaq(int num) {
		FaqDTO dto = sqlSession.selectOne(namespace+".getFaq", num);
		return dto;
	}

	public int updateFaq(FaqDTO dto) {
		int res = sqlSession.update(namespace+".updateFaq", dto);
		return res;
	}
}
