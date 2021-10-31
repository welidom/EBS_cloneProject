package com.kgitbank.ebs.service;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.kgitbank.ebs.model.NoticeDTO;

@Service
public class NoticeService {

	@Inject
	private SqlSession sqlSession;
	
	private static String namespace = "com.kgitbank.ebs.mapper.noticeMapper";

	public List<NoticeDTO> listNotice(String mode) {
		List<NoticeDTO> list = null;
		if(mode == "must") {
			list = sqlSession.selectList(namespace+".listMustNotice");
		}else if(mode=="notMust") {
			list = sqlSession.selectList(namespace+".listNotMustNotice");
		}
		else {
			list = sqlSession.selectList(namespace+".listNotice");
		}
		return list;
	}
	public List<NoticeDTO> searchNotice(String search, String mode){
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("search", search);
		hm.put("mode", mode);
		
		List<NoticeDTO> list = sqlSession.selectList(namespace+".searchNotice", hm);
		return list;
	}
	public NoticeDTO getNotice(int num, String mode) {
		if(mode == "read") {
			addNoticeReadCount(num);
		}
		NoticeDTO dto = sqlSession.selectOne(namespace+".getNotice", num);
		return dto;
	}
	private void addNoticeReadCount(int num) {
		sqlSession.update(namespace+".addNoticeReadCount", num);
	}
	public int insertNotice(NoticeDTO dto) {
		int res = sqlSession.insert(namespace+".insertNotice", dto);
		return res;
	}

	public int deleteNotice(List<Integer> nums) {
		int res = 0;
		for(int num: nums) {
			res += sqlSession.delete(namespace+".deleteNotice", num);
		}
		return res;
	}
	public int updateNotice(NoticeDTO dto) {
		int res = sqlSession.update(namespace+".updateNotice", dto);
		return res;
	}
}
