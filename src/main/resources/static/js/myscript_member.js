/**
 * 
 *//**
 * myscript.js
 */

function loginCheck(){//로그인 유효성검사(아이디,비번)
    //1)아이디 5-10글자 이내인지 검사
    var userid=document.getElementById("userid").value;
	userid=userid.trim();                               
	if(!(userid.length>=5 && userid.length<=10)){
	    alert("아이디는 5~10글자 이내로 입력해주세요");
    	document.getElementById("userid").focus();     
    	return false;                                 
	}//if end
    
    //2)비밀번호 5-10글자이내인지 검사
    var upw=document.getElementById("upw").value;
	upw=upw.trim();                               
	if(!(upw.length>=5 && upw.length<=10)){
	    alert("비밀번호는 5~10글자 이내로 입력해주세요");
    	document.getElementById("upw").focus();     
    	return false;
    }//if end

    return true; 
    
}//loginCheck() end


function findIDCheck(){//아이디 비번찾기 유효성 검사

    //1) 이름 두글자 이상
    var uname=document.getElementById("uname").value;
    uname=uname.trim(); //좌우 공백제거
    if(!(uname.length>=2)){
        alert("이름은 두 글자 이상 입력해주세요");
        document.getElementById("uname").focus(); 
        return false; //전송X
    }//if end
    
    //2) 이메일주소 5글자 이상
    var email=document.getElementById("email").value; //아이디 가져오기
    email=email.trim(); //좌우 공백제거
    if(email.length<=4){
        alert("이메일은 다섯 글자 이상 입력해주세요");
        document.getElementById("email").focus(); 
        return false; //전송X
    }//if end

}//findIDCheck()


function agreeCheck(){//회원가입약관에 동의했는지
  
   if($("#check_1").is(":checked") == false){
        alert("모든 약관에 동의 하셔야 다음 단계로 진행 가능합니다");
        return false;
   }else if($("#check_2").is(":checked") == false){
        alert("모든 약관에 동의 하셔야 다음 단계로 진행 가능합니다");
        return false;
   };


}//agreeCheck()



function memberCheck(){//회원가입 유효성 검사
	
	//1)아이디 5~10글자 인지?
	var userid=document.getElementById("userid").value; //아이디 가져오기
    userid=userid.trim(); //좌우 공백제거하기
	if(!(userid.length>=5 && userid.length<=10)){
	    alert("아이디는 5~10글자 이내로 입력해주세요");
    	document.getElementById("userid").focus();     
    	return false;                                 
	}//if end
	
	
	
    //2)비밀번호 5~10글자 인지?
    var upw=document.getElementById("upw").value; //아이디 가져오기
    upw=upw.trim(); //좌우 공백제거하기
    if(!(upw.length>=5 && upw.length<=10)){
	    alert("비밀번호는 5~10글자 이내로 입력해주세요");
    	document.getElementById("upw").focus();     
    	return false;
    }//if end

    //3)이름 두글자 이상 인지?
    var uname=document.getElementById("uname").value; //아이디 가져오기
    uname=uname.trim(); //좌우 공백제거하기
    if(uname.length<=2){
        alert("이름은 두 글자 이상 입력해주세요");
        document.getElementById("uname").focus(); 
        return false; //전송하지 않음
    }//if end
    
    
    //4)이메일 5글자 인지?
    var email=document.getElementById("email").value; //아이디 가져오기
    email=email.trim(); //좌우 공백제거하기
    if(email.length<=4){
        alert("이메일은 다섯 글자 이상 입력해주세요");
        document.getElementById("email").focus(); 
        return false; //전송하지 않음
    }//if end
    
}//memberCheck() end





function idCheck(){//아이디 중복확인용 함수

	window.open("idCheckForm.do","idwin","width=400,height=350");//옵션구분할때 ;대신 ,	
	
	
}//idCheck() end


function emailCheck(){//이메일 중복확인용 함수
	window.open("emailCheckForm.do","emailwin","width=400,height=350");
	
	
}//emailCheck() end



