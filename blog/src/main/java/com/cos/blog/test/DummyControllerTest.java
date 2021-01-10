package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRespository;

@RestController
public class DummyControllerTest {
	
	@Autowired // 오토와이어드는 유저레파지토리 타입으로 스프링이 관리하고 있는 객체가 있다면 여기 변수에 넣어줘라 라는뜻
	// 이게 의존성 주입 DI
	private UserRespository userRepository;
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		} catch (Exception e) {
			return "삭제에 실패하였습니다. 해당 id는 DB에 없습니다.";
		}
		return "삭제되었습니다. id :" +id;
	}
	
	//update email, password
	@Transactional
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
		System.out.println("id : " + id);
		System.out.println("password : " + requestUser.getPassword());
		System.out.println("email : " + requestUser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패하였습니다.");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());

//		userRepository.save(user);
// 	더티체킹
		return user;
	}
	
// http:// localhost:8000/blog/dummy/user
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	// 한 페이지당 2건에 데이터를 리턴받아 보는것
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size=2, sort="id", direction = Sort.Direction.DESC) Pageable pageable){
		Page<User> pagingUser = userRepository.findAll(pageable);
		List<User> users = pagingUser.getContent();
		return users;
	}
	
	// {id} 주소로 파라미터를 전달 받을 수 있음
	// http:// localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		// 지금 USER 페이저가 3까지만 있는데 
		// 이 상황에서 내가 user/4을 찾으면 
		// 내가 데이터베이스에서 못찾아오게 되면 user가  null 이 되잖니?
		// 그럼 return 이 null 이 리턴이 되니까.,. 그럼 프로그램에 문제가 있지 않겠니?
		// 하면서 에러가 따다다닥 어우 끔직해
		// Optional로 너의 User객체를 감싸서 가져올테니 null 인지 아닌지 판단해서 return 하도록 하렴
		
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 유저는 없습니다. id : " + id);
			}
		});
		return user;
	}
		
// 람다식으로 표현을 바꾸면 이렇게 됨
//		User user = userRepository.findById(id).orElseThrow(()-> {
//			return new IllegalArgumentException("해당 사용자는 없습니다.");
//		});
//	return user;
//}
		// 요청은 웹브라우저에서 했다
		// user 객체는 자바 오브젝트이다.
		// 변환 (웹브라우저가 이해할 수 있는 데이터 -> json(Gson 라이브러리)
		// 스프링부트 = MessageConverter 라는 애가 응답시에 자동으로 작동한다.
		// 만약에 자바 오브젝트를 리턴하게 되면 MessageConver가 Jackson 라이브러리를 호출해서
		// user 오브젝트를 json으로 변환해서 브라우저에게 던져준다.
	
	@PostMapping("/dummy/join")
	public String join(User user) {
		System.out.println("id : " +user.getId());
		System.out.println("username : " + user.getUsername());
		System.out.println("password : " + user.getPassword());
		System.out.println("email : " + user.getEmail());
		System.out.println("role : " +user.getRole());
		System.out.println("createDate : " + user.getCreateDate());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
	}
}