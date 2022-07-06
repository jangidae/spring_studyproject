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


