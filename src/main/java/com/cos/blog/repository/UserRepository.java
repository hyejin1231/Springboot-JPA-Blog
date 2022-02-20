package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;

// DAO 
// 자동으로 BEAN 등록이 된다.
// @Repository 생략 가능 
//													<User테이블, PK> 
public interface UserRepository extends JpaRepository<User, Integer>{

}
