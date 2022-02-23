package com.cos.blog.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
