package com.kgitbank.ebs.service;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

@Service
public class schoolMapper {
	@Inject
	private SqlSession sqlSession; 
}
