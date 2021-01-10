package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cos.blog.model.User;

//@Repository// 이게 생략가능하다,
// 자동으로 bean 등록이 된다. DAO
public interface UserRespository extends JpaRepository<User, Integer>{
	// 해당 jpa레파지토리는 user테이블을 관리하는 레파지토리야
	// 그리고 이 user테이블의 primary key는 정수형이야(숫자야)

}
