package com.kgitbank.ebs.service;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.kgitbank.ebs.model.UserDTO;

@Service
public class UserService {
	@Inject
	private SqlSession sqlSession;
	
	private static String namespace="com.kgitbank.ebs.mapper.userMapper";
		
	public boolean checkUserOverlab(String userId) {
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

	public int updateUser(UserDTO dto) {
		int res = sqlSession.update(namespace+".updateUser", dto);
		return res;
	}

	public List<UserDTO> userList() {
		List<UserDTO> list = sqlSession.selectList(namespace+".userList");
		return list;
	}

	public void setSchool(String schoolId, String userId) {
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("schoolId", schoolId);
		hm.put("userId", userId);
		sqlSession.update(namespace+".setSchool", hm);
	}

	public void setPermit(String id, int permit) {
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("id", id);
		hm.put("permit", permit);
		sqlSession.update(namespace+".setPermit", hm);
	}

	public void setAuth(String authKey, String userId) {
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("id", userId);
		hm.put("key", authKey);
		sqlSession.update(namespace+".setAuth", hm);
	}
	public UserDTO checkUser(String email, String authKey) {
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("email", email);
		hm.put("key", authKey);
		UserDTO dto = sqlSession.selectOne(namespace+".checkUser", hm);
		return dto;
	}

	public void deleteUser(String id) {
		sqlSession.delete("deleteUser", id);
	}
}
