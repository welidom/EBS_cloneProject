package com.kgitbank.ebs.service;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.kgitbank.ebs.model.UserDTO;

@Service
public class userMapper {
	@Inject
	private SqlSession sqlSession;
	
	private static String namespace="com.kgitbank.ebs.mapper.userMapper";
		
	public boolean checkOverlab(String userId) {
		UserDTO dto = getUser(userId);
		if(dto == null) {
			return false;
		}else {
			return true;
		}
	}

	public void newUser(UserDTO dto) {
		sqlSession.insert(namespace+".newUser", dto);
	}
	
	public UserDTO getUser(String userId) {
		UserDTO dto = sqlSession.selectOne(namespace+".getUser", userId);
		return dto;
	}

	public void updateUser(UserDTO dto) {
		sqlSession.update(namespace+".updateUser", dto);
	}
	
}
