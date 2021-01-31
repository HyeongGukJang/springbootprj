package com.cos.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.blog.config.auth.PrincipalDetailService;

// 빈 등록 : 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것

@Configuration // 빈등록(IOC로 가게되서 관리가됨)
@EnableWebSecurity // 필터 추가(시큐리티라는 필터를 추가하는것 == 스프링 시큐리티가 활성화가 되어있는데, 어떤 설정을 해당 파일에서 하겠다는 것)
// 시큐리티의 필터로 등록이 된다는 말
@EnableGlobalMethodSecurity(prePostEnabled = true) //특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다는 뜻.
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private PrincipalDetailService principalDetailService;
	
	@Bean //IOC가 된당
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
		// 리턴값을 스프링이 관리한다 그래서 필요할때마다 가져와서 쓰면된다.
	}
	
	// 시큐리티가 대신 로그인해주는데 그때 password를 가로채기를 하는데
	// 해당 패스워드가 뭘로 해쉬가 되서 회원가입이 되었는지 알아야 
	// 같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교할 수 있다.
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
	}
	
@Override
protected void configure(HttpSecurity http) throws Exception {
	http
	.csrf().disable() // csrf 토큰 비활성화(테스트시 걸어두는게 좋다)
	.authorizeRequests() //.authorizeRequests() == request(요청)이 들어오면? 이라는 뜻의 시큐리티 함수
		.antMatchers("/","/auth/**","/js/**","/css/**","/image/**")
		.permitAll()
		.anyRequest()
		.authenticated()
	.and() 
		.formLogin()
		.loginPage("/auth/loginForm")
		.loginProcessingUrl("/auth/loginProc")// 스프링 시큐리티가 해당 주소로 로그인을 가로채서 대신 로그인을 해준다.
		.defaultSuccessUrl("/"); // 정상적으로 요청이 완료가되면  디폴트 "/" 이쪽으로 이동한다.
}
}
