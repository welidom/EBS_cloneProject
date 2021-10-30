package com.kgitbank.ebs.service;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.kgitbank.ebs.model.SchoolDTO;

@Service
public class schoolMapper {
	@Inject
	private SqlSession sqlSession;
	private static String namespace="com.kgitbank.ebs.mapper.schoolMapper";
	
	public List<SchoolDTO> searchSchool(String searchFor) {
		List<SchoolDTO> list = sqlSession.selectList(namespace+".searchSchool", searchFor);
		return list;
	}

	public SchoolDTO getSchool(String id) {
		if(id == null) {
			return null;
		}else {
			return sqlSession.selectOne(namespace+".getSchool", id);
		}
	}

	public List<SchoolDTO> schoolList() {
		List<SchoolDTO> list = sqlSession.selectList(namespace+".schoolList");
		return list;
	} 
}
