blog 만듬 프로젝트
join 만드는 와중 db insert jpa 에러...
21.01.26 회원가입 에러해결
이유 : js 폴더 내
user.js의

	save: function(){
		//alert('user의 save함수 호출됨');
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};
    
    위 부분이 정상인데 
    
    	save: function(){
		//alert('user의 save함수 호출됨');
		let data = {
			username: $("#username").val(),
			passwoord: $("#passoword").val(),
			email: $("#email").val()
		};
    
   			password: $("#password").val(),  ->	passwoord: $("#passoword").val(),
        
        패스워드의 오타 때문에 계속 에러발생한거였음...

21.01.31
Spring Security 를 이용해서 password를 해쉬화 하였다.
회원가입 구현완료
DB insert까지 확인함
