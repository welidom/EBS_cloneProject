package com.kgitbank.ebs.service;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.kgitbank.ebs.model.ManualDTO;

@Service
public class manualMapper {
	
	@Inject SqlSession sqlSession;
	
	private static String namespace = "com.kgitbank.ebs.mapper.manualNotice";
	
	public List<ManualDTO> getManual(int type){
		List<ManualDTO> list = sqlSession.selectList(namespace+".getManual",type);
		return list;
	}
	
	public int insertManual(ManualDTO dto) {
		int res = sqlSession.insert(namespace+".insertManual", dto);
		return res;
	}
	
	public int deleteManual(List<Integer> nums) {
		int res = 0;
		for(int num: nums) {
			res += sqlSession.delete(namespace+".deleteManual", num);
		}
		return res;
	}
}
