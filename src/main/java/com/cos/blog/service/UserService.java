package com.cos.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@Service // Spring이 Component 스캔을 통해서 Bean에 등록을 해줌. -> Ioc 해줌 
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public void save(User user) {
		userRepository.save(user);
	
	}
	
	// select할 때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료까지 정합성 유지 가능
	// 즉 여러번 select 해도 같은 데이터를 얻을 수 있다. 
//	@Transactional(readOnly = true)  
//	public User login(User user) {
//		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//	}
}
