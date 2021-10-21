package com.kgitbank.ebs.service;

import java.util.HashMap;
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

	public List<NoticeDTO> listNotice(String mode) {
		List<NoticeDTO> list = null;
		if(mode == "must") {
			list = sqlSession.selectList("listMustNotice");
		}else if(mode=="notMust") {
			list = sqlSession.selectList("listNotMustNotice");
		}
		else {
			list = sqlSession.selectList("listNotice");
		}
		return list;
	}
	public List<NoticeDTO> searchNotice(String search, String mode){
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("search", search);
		hm.put("mode", mode);
		
		List<NoticeDTO> list = sqlSession.selectList("searchNotice", hm);
		return list;
	}
	public NoticeDTO getNotice(int num, String mode) {
		if(mode == "read") {
			addNoticeReadCount(num);
		}
		NoticeDTO dto = sqlSession.selectOne("getNotice", num);
		return dto;
	}
	private void addNoticeReadCount(int num) {
		sqlSession.update("addNoticeReadCount", num);
	}
	public int insertNotice(NoticeDTO dto) {
		int res = sqlSession.insert("insertNotice", dto);
		return res;
	}

	public int deleteNotice(List<Integer> nums) {
		int res = 0;
		for(int num: nums) {
			res += sqlSession.delete("deleteNotice", num);
		}
		return res;
	}
	public int updateNotice(NoticeDTO dto) {
		int res = sqlSession.update("updateNotice", dto);
		return res;
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
