package com.cos.blog.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRespository;

@Service
public class PrincipalDetailService implements UserDetailsService{

	@Autowired
	private UserRespository userRespository;
	
	// 스프링이 로그인 요청을 가로챌때, username,password 이 두개의 변수를 가로채는데
	// password 부분 처리는 알아서 하는데, 
	// 개발자는 해당 username이 DB에 있는지만 확인해주면 됨
	// 그 확인을 이 loadUserByUsername 라는 함수에서 한다.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User principal = userRespository.findByUsername(username)
				.orElseThrow(()->{
					return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다."+username);
				});
		return new PrincipalDetail(principal);
	}
}
