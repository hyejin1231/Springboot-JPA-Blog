package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@DynamicInsert 이렇게 어노테이션이 많아지는건 좋지 않다.
@Entity // User클래스가 MariaDB에 테이블이 생성이 된다.
public class User {
	
	@Id // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 db의 넘버링 전략을 따라간다.
	private int id; // 시퀀스, auto-increment 
	
	@Column(nullable = false, length = 30, unique = true)
	private String username; // 아이디 
	
	@Column(nullable = false, length = 100)
	private String password; 
	
	@Column(nullable = false, length = 50)
	private String email;
	
//	@ColumnDefault("'user'")
	@Enumerated(EnumType.STRING) //db에는 RoleType이란게 없으니까 String이라고 알려줘야함.
	private RoleType role; // Enum을 쓰는게 좋다. admin, user, manager 
	
	@CreationTimestamp // 시간이 자동으로 입력된다!
	private Timestamp createDate;

}
