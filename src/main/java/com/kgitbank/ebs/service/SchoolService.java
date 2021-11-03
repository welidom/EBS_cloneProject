package com.kgitbank.ebs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.kgitbank.ebs.model.SchoolDTO;

@Service
public class SchoolService {
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

	public boolean checkSchool(String schoolName) {
		SchoolDTO dto = sqlSession.selectOne(namespace+".checkSchool", schoolName);
		if(dto != null) {
			return true;
		}else {
			return false;
		}
	} 
	private String makeKey() {
        Random random = new Random();
        String str = "";
        int num = 0;

        while(str.split("").length < 6) {
            num = random.nextInt(26);
            num += 65;
            str += (char) num;
        }

        return str;
    }
	public void insertSchool(String schoolName, String schoolManager) {
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("schoolName", schoolName);
		hm.put("schoolManager", schoolManager);
		String id = null;
		while(id == null || getSchool(id) != null) {
			id = makeKey();
		}
		hm.put("id", id);
		hm.put("pw", makeKey());
		sqlSession.insert(namespace+".insertSchool", hm);
	}

	public void deleteSchool(String id) {
		sqlSession.delete(namespace+".deleteSchool", id);
	}

	public void updateSchool(SchoolDTO dto) {
		sqlSession.update(namespace+".updateSchool", dto);
	}
}
