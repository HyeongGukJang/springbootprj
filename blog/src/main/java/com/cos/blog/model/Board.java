package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더패턴
@Entity
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob // 대용량 데이터
	private String content; // 섬머노트 라이브러리 <html> 태그가 섞여서 디자인이 된다.
	
	@ColumnDefault("0") // 조회수 처음이 0이여야된다.
	private int count; // 조회수
	
	@ManyToOne(fetch = FetchType.EAGER) 
	// Many = Many, User = One, Borad 가 Many 이고 User가 One 
	// 한명의 유저가 여러개의 보드를 쓸 수 있다는 뜻
	// 여러개의 게시글은 한명의 유저가 쓸 수 있따.
	@JoinColumn(name="userId")
	private User user; // DB는 오브젝트를 저장할 수 없다. 자바는 오브젝트를 저장할 수 있다.
	
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER) 
	// mappedBy 가 적혀있으면 연관관계의 주인이 아니란 얘기, 난 포린키가 아니에요 라는소리
	//(DB에 컬럼을 만들지 마세요 란소리)
	private List<Reply> reply;
	
	@CreationTimestamp
	private Timestamp createDate;
}