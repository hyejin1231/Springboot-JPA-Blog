package com.cos.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@Service // Spring이 Component 스캔을 통해서 Bean에 등록을 해줌. -> Ioc 해줌 
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Transactional
	public void save(User user) {
		
		String rawPassword = user.getPassword(); // 비밀번호 원본 
		String encPassword = encoder.encode(rawPassword); // 해쉬 비밀번호
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		userRepository.save(user);
	
	}
	
	@Transactional
	public void update(User user) {
		// 수정시에는 영속성 컨텍스트에 User 오브젝트를 영속화시키고, 
		// 영속화된 User 오브젝트를 수정 
		// select를 해서 User 오브젝틀르 db로부터 가져오는 이유는 영속화를 하기 위해서!
		// 영속화된 오브젝틀르 변경하면 자동으로 db에 update문을 날려준다!
		User persistance = userRepository.findById(user.getId()).orElseThrow(() -> {
			return new IllegalArgumentException("회원찾기 실패!");
		});
		
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		persistance.setPassword(encPassword); // 패스워드 수정 
		persistance.setEmail(user.getEmail());
		
		// 회원 수정 함수 종료 -> 서비스 종료 -> 트랜잭션 종료 -> 즉 commit 자동으로 수행 
		// 영속화된 persistance 객체의 변화가 감지되면 더티체킹을 통해 update문 날려준다.(자동) 
	}
	
	// select할 때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료까지 정합성 유지 가능
	// 즉 여러번 select 해도 같은 데이터를 얻을 수 있다. 
//	@Transactional(readOnly = true)  
//	public User login(User user) {
//		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//	}
}
