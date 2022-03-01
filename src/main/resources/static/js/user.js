let index = {
	init: function(){
		$("#btn-save").on("click",()=>{ // function(){} 이렇게 한 이유는 this를 바인딩하기 위해서!
			this.save();
		});
		
		$("#btn-login").on("click",()=>{ // function(){} 이렇게 한 이유는 this를 바인딩하기 위해서!
			this.login();
		});
		
		$("#btn-update").on("click",()=>{ // function(){} 이렇게 한 이유는 this를 바인딩하기 위해서!
			this.update();
		});
	},
	
	save:function(){
		//alert('user의 save 함수 호출됨');
		
		let data = {
			username:$("#username").val(),
			password:$("#password").val(),
			email:$("#email").val()
		};
		
		//console.log(data);
		
		// ajax 호출시 default가 비동기 호출 
		$.ajax({
			// 회원 가입 수행 요청 
			type: "POST", 
			url: "/auth/joinProc",
			data: JSON.stringify(data), // http body 데이터 
			contentType: "application/json; charset=utf-8", // body 데이터가 어떤타입인지 
			dataType: "json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 문자열인데 생긴게 json이라면 javascript 오브젝트로 변환
			 
		}).done(function(resp){
			// 응답 결과가 성공 
			alert("회원가입이 완료되었습니다.");
			location.href="/"
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청 
	},
	
	login:function(){
		//alert('user의 save 함수 호출됨');
		
		let data = {
			username:$("#username").val(),
			password:$("#password").val(),
		};
		
		$.ajax({
			// 회원 가입 수행 요청 
			type: "POST", 
			url: "/api/user/login",
			data: JSON.stringify(data), // http body 데이터 
			contentType: "application/json; charset=utf-8", // body 데이터가 어떤타입인지 
			dataType: "json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 문자열인데 생긴게 json이라면 javascript 오브젝트로 변환
			 
		}).done(function(resp){
			// 응답 결과가 성공 
			alert("로그인이 완료되었습니다.");
			location.href="/"
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청 
	},
	
	update:function(){

		let data = {
			id:$("#id").val(),
			password:$("#password").val(),
			email:$("#email").val()
		};
		
		$.ajax({
			// 회원 가입 수행 요청 
			type: "PUT", 
			url: "/user",
			data: JSON.stringify(data), 
			contentType: "application/json; charset=utf-8",  
			dataType: "json" 
			 
		}).done(function(resp){
			// 응답 결과가 성공 
			alert("회원수정이 완료되었습니다.");
			location.href="/"
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); 
	}
}	

index.init();