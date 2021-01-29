package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRespository;

// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해준당. IOC를 해준다.
@Service
public class UserService {

	@Autowired
	private UserRespository userRespository;
	
	@Transactional
	public void 회원가입(User user) {
			userRespository.save(user);
	}
	
	@Transactional(readOnly = true) // select 할 때 트랜젝션 시작되는데, 그리고 해당 서비스가 종료될때 트랜젝션이 종료(정합성)
	//
	public User 로그인(User user) {
		return userRespository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
	}
}
