package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.User;

// DAO
// 자동으로 bean 등록이 된다.
// @Repository /// 생략이 가능
public interface UserRespository extends JpaRepository<User, Integer> {

}




// JPA Naming 쿼리 / 전략
// JPA가 실제로 들고있지 않은 함수인데 
// 이렇게 선언을 해주면, SELECT * FROM USER WHERE USERNAME = ? AND PASSWORD = ?;
// 이런 쿼리가 등장을 한다.
/*
 * User findByUsernameAndPassword(String username, String password);
 */	
//@Query(value="SELECT * FROM user WHERE username= ? AND password = ?", nativeQuery =  true)
//User login(String username, String password);