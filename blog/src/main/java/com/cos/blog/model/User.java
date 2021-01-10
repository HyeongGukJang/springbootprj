package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// jpa = ORM -> Java에 있는 (다른언어도가능) Object  들을 -> 테이블로 매핑해주는 기술
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더패턴 
@Entity // User 클래스가 자동으로 MySql에 테이블이 생성이 된다.
//@DynamicInsert --> insert 시에 null 인 필드를 제외시켜주는 어노테이션
public class User {
	@Id // Primary key를 알려주기 위한 어노테이션임
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 해당 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다. 라는 뜻의 어노테이션임
	// 쉽게말해 자동으로 번호를 맞춰서 매겨준다.
	private int id; //my sql 이니깐 auto_increment이고 만약 오라클이면 seq일것이다.
	
	@Column(nullable = false, length = 30, unique = true) // nullable = 널이 될수 없다는 것, length = 길이는 정하는것
	private String username; //아이디
	
	@Column(nullable = false, length = 100) // 123456 = > 해쉬로 변경해서 비번을 암호화할꺼여서 일부러 랭쓰를 100으로 길게잡음
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
//	@ColumnDefault("'user'") // user 문자열이 들어가야되니까 " " 안에 ' ' 넣는다 -> "' '"
//	// 도메인이란 ? 어떤 범위가 정해졌다는것 성별에 대한 도메인은 "남", "여" 가 있다.
	@Enumerated(EnumType.STRING)
	private RoleType role; // Enum을 쓰는게 좋지만 우선 스트링. Enum은 도메인을 설정해줄 수 있다 ex) admin, user, manager 이 셋중에 하나만 입력이 가능하게끔!!
	// 그냥 String 하면 adminnnnn 도 될수도 있고 이런 말도안되는게 들어갈 가능성이 있기때문에 Enum으로 하면 이 걸 다 차단가능!
	
	@CreationTimestamp // 시간이 자동입력이 된다.
	private Timestamp createDate;
}