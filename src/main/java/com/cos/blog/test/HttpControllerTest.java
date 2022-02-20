package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 사용자가 요청 -> 데이터 응답 
// @Controller + @Response

// 사용자가 요청 -:> html 응답 
// @Controller 
@RestController
public class HttpControllerTest {
	
	private static final String TAG = "HttpControllerTest :";
	
	@GetMapping("/http/lombok")
	public String lombomTest() {
		Member m = new Member(1, "member", "1234", "email");
		System.out.println(TAG +"getter :" + m.getId());
		m.setId(5000);
		System.out.println(TAG + "setter : " + m.getId());
		return "lombok test 완료";
	}
	
//	@GetMapping("/http/get")
//	public String getTest(@RequestParam int id, @RequestParam String username) {
//		return "get 요청" + id + ", " + username;
//	}
	
	@GetMapping("/http/get")
	public String getTest(Member m) {
		return "get 요청 : " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword();
	}
	
//	@PostMapping("/http/post")
//	public String postTest(Member m) {
//		return "post 요청 : " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword();
//	}
	
//	@PostMapping("/http/post")
//	public String postTest(@RequestBody String text) {
//		return "post 요청 : " + text;
//	}
	
	// json -> Member로 매핑해서 넣어줌 => 이런 역할을 MessageConverter가 해준다. 
	// Mime type 이 json일때 
	@PostMapping("/http/post")
	public String postTest(@RequestBody Member m) {
		return "post 요청 : " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword();
	}
	
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put 요청" + m.getId() + ", " + m.getUsername() + ", " + m.getPassword();
	}
	
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
	

}
