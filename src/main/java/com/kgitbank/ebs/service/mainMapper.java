package com.kgitbank.ebs.service;

import java.util.List;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kgitbank.ebs.model.FaqDTO;
import com.kgitbank.ebs.model.ManualDTO;
import com.kgitbank.ebs.model.NoticeDTO;

@Service
public class mainMapper {

	@Autowired
	private SqlSession sqlSession;

	public List<NoticeDTO> listNotice() {
		List<NoticeDTO> list = sqlSession.selectList("listNotice");
		return list;
	}
	public NoticeDTO getNotice(int num) {
		NoticeDTO dto = sqlSession.selectOne("getNotice", num);
		return dto;
	}
	public List<FaqDTO> faqList(){
		List<FaqDTO> list = sqlSession.selectList("faqList");
		return list;
	}
	public List<ManualDTO> getManual(int type){
		List<ManualDTO> list = sqlSession.selectList("getManual",type);
		return list;
	}
	
	public int insertManual(ManualDTO dto) {
		int res = sqlSession.insert("insertManual", dto);
		return res;
	}
	
	public int deleteManual(List<Integer> nums) {
		int res = 0;
		for(int num: nums) {
			res += sqlSession.delete("deleteManual", num);
		}
		return res;
	}
}
