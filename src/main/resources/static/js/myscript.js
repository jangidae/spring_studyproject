/**
	게시판에 글 작성 시 유효성 검사
 */

function bbsCheck1(){ //외국어 스터디 게시판 유효성 검사
    
    //카테고리를 선택하지 않은 경우
    var lcode=document.getElementById("lcode").value;
    if(lcode=="0"){
        alert("카테고리를 선택해주세요");
        return false; //전송하지 않음
    }//if end
    
    
	//1)제목 2글자 이상 입력
	var wtitle=document.getElementById("wtitle").value;
	wtitle=wtitle.trim();                               
	if(wtitle.length<2){
	    alert("제목은 2글자 이상 입력해 주세요");
    	document.getElementById("wtitle").focus();     
    	return false;                                 
	}//if end
	
	//2)내용 2글자 이상 입력
	var wcontent=document.getElementById("wcontent").value;
	wcontent=wcontent.trim();                               
	if(wcontent.length<2){
	    alert("내용은 2글자 이상 입력해 주세요");
    	document.getElementById("wcontent").focus();     
    	return false;                                 
	}//if end
	
	
	//3) 비밀번호 4글자 이상 입력
    var wpasswd=document.getElementById("wpasswd").value;
    wpasswd=wpasswd.trim(); //좌우 공백제거하기
    if(wpasswd.length<4){
        alert("게시글 비밀번호는 4글자 이상 입력해주세요");
        document.getElementById("wpasswd").focus();
        return false; //전송하지 않음
    }//if end
    
        
    return true; //onsubmit이벤트에서 서버로 전송
    
}//bbsCheck1() end


function bbsCheck2(){ //자유 게시판 유효성 검사
    
    //카테고리를 선택하지 않은 경우
    var ccode=document.getElementById("ccode").value;
    if(ccode=="0"){
        alert("카테고리를 선택해주세요");
        return false; //전송하지 않음
    }//if end
    
    
	//1)제목 2글자 이상 입력
	var wtitle=document.getElementById("wtitle").value;
	wtitle=wtitle.trim();                               
	if(wtitle.length<2){
	    alert("제목은 2글자 이상 입력해 주세요");
    	document.getElementById("wtitle").focus();     
    	return false;                                 
	}//if end
	
	//2)내용 2글자 이상 입력
	var wcontent=document.getElementById("wcontent").value;
	wcontent=wcontent.trim();                               
	if(wcontent.length<2){
	    alert("내용은 2글자 이상 입력해 주세요");
    	document.getElementById("wcontent").focus();     
    	return false;                                 
	}//if end
	
	
	//3) 비밀번호 4글자 이상 입력
    var wpasswd=document.getElementById("wpasswd").value;
    wpasswd=wpasswd.trim(); //좌우 공백제거하기
    if(wpasswd.length<4){
        alert("게시글 비밀번호는 4글자 이상 입력해주세요");
        document.getElementById("wpasswd").focus();
        return false; //전송하지 않음
    }//if end
    
        
    return true; //onsubmit이벤트에서 서버로 전송
    
}//bbsCheck2() end


function centerCheck(){ //고객센터 유효성 검사
    
    //카테고리를 선택하지 않은 경우
    var wcode=document.getElementById("wcode").value;
    if(wcode=="0"){
        alert("카테고리를 선택해주세요");
        return false; //전송하지 않음
    }//if end
    
    
	//1)제목 2글자 이상 입력
	var wtitle=document.getElementById("wtitle").value;
	wtitle=wtitle.trim();                               
	if(wtitle.length<2){
	    alert("제목은 2글자 이상 입력해 주세요");
    	document.getElementById("wtitle").focus();     
    	return false;                                 
	}//if end
	
	//2)내용 2글자 이상 입력
	var wcontent=document.getElementById("wcontent").value;
	wcontent=wcontent.trim();                               
	if(wcontent.length<2){
	    alert("내용은 2글자 이상 입력해 주세요");
    	document.getElementById("wcontent").focus();     
    	return false;                                 
	}//if end
	
	
	//3) 비밀번호 4글자 이상 입력
    var wpasswd=document.getElementById("wpasswd").value;
    wpasswd=wpasswd.trim(); //좌우 공백제거하기
    if(wpasswd.length<4){
        alert("게시글 비밀번호는 4글자 이상 입력해주세요");
        document.getElementById("wpasswd").focus();
        return false; //전송하지 않음
    }//if end
    
        
    return true; //onsubmit이벤트에서 서버로 전송
    
}//bbsCheck2() end


//제목(wtitle), 내용(wcontent), 파일명(failname), 사진파일(poster)


function noticeCheck(){ //공지사항 유효성 검사
	
    //1) 제목 2글자 이상 입력
    var wtitle=document.getElementById("wtitle").value;
    wtitle=wtitle.trim(); //좌우 공백제거하기
    if(!(wtitle.length>=2)){
        alert("제목은 두 글자 이상 입력해주세요");
        document.getElementById("wtitle").focus(); 
        return false; //전송하지 않음
    }//if end
    
    
    //2)내용 2글자 이상 입력
	var wcontent=document.getElementById("wcontent").value;
	wcontent=wcontent.trim();                               
	if(wcontent.length<2){
	    alert("내용은 2글자 이상 입력해 주세요");
    	document.getElementById("wcontent").focus();     
    	return false;                                 
	}//if end
    

    //3) 사진 확장명이 이미지 파일인지
    
    //-> 확장명이 이미지(PNG,JPG,GIF)인 파일인지
    var poster=document.getElementById("poster").value; //예)sky.png 와 같이 정보를 가져올 수 있음
    poster=poster.trim();
    if(poster.length==0){
        alert("첨부파일을 선택해주세요")
        return false;
    }else{
        //poster변수값에서 마지막.의 순서값을 알아오기
        var dot=poster.lastIndexOf(".");
        //확장명 : 마지막 . 이후 문자열로 자르기
        var ext=poster.substr(dot+1);
        //확장명을 전부 소문자로 치환
        ext=ext.toLowerCase();
        if(ext=="png" || ext=="jpg" || ext=="gif"){
            return true;
        }else{
            alert("이미지 파일만 업로드 가능합니다")
            return false;
        }//if end

    }//if end
}//noticeCheck() end