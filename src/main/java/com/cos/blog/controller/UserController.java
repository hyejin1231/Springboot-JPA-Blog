package com.cos.blog.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

// 인증이 안된 사용자들이 출입할 수 있는 경로를 /auth/** 허용 
// 그냥 주소가 / 이면 index.jsp 허용 
// static 이하에 있는 /js/**, /css/**, /image/**
@Controller
public class UserController {
	
	@GetMapping("/auth/joinForm")
	public String joinForm() {
		
		return "user/joinForm";
	}
	
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		
		return "user/loginForm";
	}
	
	
	@GetMapping("/user/updateForm")
	public String updateForm() {
		return "user/updateForm";
	}

	
	@GetMapping("/auth/kakao/callback")
	public @ResponseBody  String kakaoCallback(String code) { // data를 리턴해주는 컨트롤러 함수 
		
		// POST 방식으로 key-value 데이터 형식으로 요청 (카카오쪽으로)
		// Retrofit2 (안드로이드에서 많이사용)
		// OkHttp
		// RestTemplate
		RestTemplate restTemplate = new RestTemplate();
		
		// HttpHeader 오브젝트 생성 
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		// HttpBody 오브젝트 생성 
		// 이런건 변수화해서 사용하는게 좋음!
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "655c0579f9a7aa683940c2bcde4c98c9");
		params.add("redirect_uri", "http://localhost:8000/auth/kakao/callback");
		params.add("code", code);
		
		
		// HttpHeader와 Httpbody를 하나의 오브젝트에 담기 
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = 
				new HttpEntity<>(params, headers);
		
		// Http 요청하기 - POST 방식으로
		// 그리고 Response 변수의 응답을 받음.
		ResponseEntity response = restTemplate.exchange(
				"https://kauth.kakao.com/oauth/token",
				HttpMethod.POST,
				kakaoTokenRequest,
				String.class // response 응답이 String 
		);
		
//		return "카카오 인증 완료, 코드값 : " + code;
		return "카카오 토큰 요청 완료, 토큰 요청에 대한 응답 : " + response;
	}

}



























