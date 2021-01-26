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
    
    이 부분이 정상인데 
    
    	save: function(){
		//alert('user의 save함수 호출됨');
		let data = {
			username: $("#username").val(),
			passwoord: $("#passoword").val(),
			email: $("#email").val()
		};
    
   			password: $("#password").val(),  ->	passwoord: $("#passoword").val(),
        
        패스워드의 오타 때문에 계속 에러발생한거였음...
        장장 3시간 만에 찾아냄 ㅅㅂ 삽질 오지게 했네
