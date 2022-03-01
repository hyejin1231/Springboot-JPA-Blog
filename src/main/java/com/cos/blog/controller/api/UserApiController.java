package com.cos.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

@RestController
public class UserApiController {
	
	@Autowired
	private UserService userService;
	
	
//	@Autowired 이렇게 해도 되긴함!
//	private HttpSession httpSession;
	
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) { // username, password, email
		System.out.println("UserApiController : save 호출됨");
		userService.save(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	
	}
	
	@PutMapping("/user")
	public ResponseDto<Integer> update(@RequestBody User user) { //json 데이터를 받을 것이기 때문에 RequestBody 사용
		userService.update(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	// 전통적인 방식의 로그인 방법 
//	@PostMapping("/api/user/login")
//	public ResponseDto<Integer> login(@RequestBody User user, HttpSession session){
//		System.out.println("UserApiController : login 호출됨");
//		User principal = userService.login(user); // principal = 접근주체
//		
//		if(principal != null) {
//			session.setAttribute("principal", principal);
//		}
//		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
//	}

}
