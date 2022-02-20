package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;



@RestController // html이 아닌 데이터를 전달해줌 
public class DummyControllerTest {
	
	@Autowired // 의존성 주입 
	private UserRepository userRepository;

	@PostMapping("/dummy/join")
	public String join(User user) {
		System.out.println("id : " + user.getId());
		System.out.println("username : " + user.getUsername());
		System.out.println("password : " + user.getPassword());
		System.out.println("email :" + user.getEmail());
		System.out.println("role : " + user.getRole());
		System.out.println("createDate : " + user.getCreateDate());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		
		return "회원가입이 완료되었습니다..";
	}
	
	// {id} 주소를 파라미터로 전달받을 수 있다. 
	@GetMapping("/dummy/user/{id}")
	public User Detail(@PathVariable int id) {
		
		// user/4를 찾았는데 db에 없으면 user가 null이되고, 그럼 return이 null이 될수도 있다.
		// optional로 user객체를 감싸서 가져오니 null인지 아닌지 판단해서 return!!
//		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
//
//			@Override
//			public IllegalArgumentException get() {
//				return new IllegalArgumentException("해당 유저는 없습니다..!!" + id);
//			}
//		});
		
		// 람다식 사용 
		User user = userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("해당 유저는 없습니다..!!" + id);
		});
		
		// user 객체는 자바 오브젝트이므로 이를 웹브라우저가 이해할 수 있도록 json으로 변환해줘야한다. 
		// 스프링 부트-> MessageConverter가 응답시에 자동으로 작동해서 
		// 만약 자바 오브젝트를 리턴하게 되면 MessaageConverter가 Jackson라이브러리를 호출해서 
		// user 오브젝트를 json으로 변환해서 브라우저에 던져준다. 
		return user;
	}
	
	
	@GetMapping("/dummy/users")
	public List<User> list() {
		return userRepository.findAll();
	}
	
	
	// 한 페이지당 2건의 데이터를 리턴받아 볼 예정 
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<User> pagingUser =  userRepository.findAll(pageable);
		
		List<User> users = pagingUser.getContent();
		return users;
	}
	
	// save 함수는 id를 전달하지 않으면 insert를 해주고
	// save 함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 update 해주고 
	// save 함수는 id를 전달하고 해당 id에 대한 데이터가 없으면 insert를 한다. 
	// email, password 
	@Transactional  // 더티 체킹 
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser ) { 
		// json 데이터 요청 -> MessageConverter의 Jackson 라이브러리가 변환해서 java object로 반환해서 받아준다. 
		System.out.println("id : " + id);
		System.out.println("password : " + requestUser.getPassword());
		System.out.println("email : " + requestUser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("수정에 실패하였습니다.");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
//		userRepository.save(user);
		return null;
	}
}



























































