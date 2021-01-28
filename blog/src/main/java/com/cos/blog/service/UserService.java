package com.cos.blog.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
