let index = {
	init: function(){
		$("#btn-save").on("click", ()=>{ // function(){}, 안쓰고 화살표함수를 쓰는 이유는 this를 바인딩하기 위해서
			this.save();
		});
	},
	
	save: function(){
		//alert('user의 save함수 호출됨');
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};
		//console.log(data);
		// ajax 호출시 default가 비동기 호출이다.
		// ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청을 할것
		// ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환해주네용... 원랜 아니였는데
		$.ajax({
			type: "POST",
			url: "/blog/api/user",
			data: JSON.stringify(data), // http body 데이터
			contentType: "application/json; charset=utf-8", // body데이터가 어떤 타입인지(MINE)
			dataType: "json"//요청을 서버로 해서 응답이 왔을때 기본적으로 모든것이 버퍼로 와서 String인데(문자열) 생긴게 json이라면 => 여기다 json 이라고 알려주면 javascript 오브젝트로 변경해주는 역할
			// 회원가입 수행 요청
		}).done(function(resp){
			alert("회원가입이 완료되었습니다.");
			//console.log(resp);
			location.href = "/blog";
			// 응답의 결과가 정상이면 done 실행
		}).fail(function(error){
			alert(JSON.stringify(error));
			// 응답의 결과가 실패면 fail 실행
		}); 
	}
}

index.init();