package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

// 사용자가 요청 -> 응답(Data)

@RestController
public class HttpControllerTest {
	
	private static final String TAG = "HttpControllerTest : ";
	
	// localhost:8000/blog/http/lombok
	@GetMapping("/http/lombok")
	public String lombokTest() {
//		Member m = new Member(1, "ssar", "1234", "email");
		Member m = Member.builder().username("ssar").password("1234").email("ssar@nate.com").build();
		System.out.println(TAG + "getter"+m.getUsername());
		m.setUsername("cos");
		System.out.println(TAG + "setter : " + m.getUsername());
		return "lombok test 완료";
	}
	
	// http://localhost:8080/http/get   (select)
	@GetMapping("/http/get")
	public String getTest(Member m) {
		return "get 요청 : " + m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail();
	}
	// http://localhost:8080/http/post   (insert)
	@PostMapping("/http/post")
	public String postTest() {
		return "post 요청";
	}
	// http://localhost:8080/http/put   (upadate)
	@PutMapping("/http/put")
	public String putTest() {
		return "put 요청";
	}
	// http://localhost:8080/http/delete   (delete)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
}
