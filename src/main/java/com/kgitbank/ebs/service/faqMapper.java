package com.kgitbank.ebs.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale.Category;

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
	public List<FaqDTO> FaqlistCategory() {
		List<FaqDTO> list = new ArrayList<FaqDTO>();
		String[] name = {"자주 찾는 질문 ", "회원","클래스 이용", "학습", "클래스 개설/관리", "학습 관리", "화상 수업", "강좌 관리", "기타"};
		for(int i = 0; i < 9; i++) {
			FaqDTO dto = new FaqDTO();
			dto.setCno(1000 + i);
			dto.setCategory(name[i]);
			list.add(dto);
		}
		return list;
	}
	public List<FaqDTO> FaqreadTitle(int cno, String keyword) {
		return sqlSession.selectList(namespace+".faqReadTitle");
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
