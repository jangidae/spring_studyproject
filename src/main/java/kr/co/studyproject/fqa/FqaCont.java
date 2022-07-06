package kr.co.studyproject.fqa;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.co.studyproject.bbsLang.BbsLangDTO;



//URL에서 요청한 명령어를 읽어서 실행해 주는 클래스

//스프링컨테이너(웹서버)가 자동으로 객체 생성된다
@Controller
public class FqaCont {

	FqaDAO dao=null;
	
	public FqaCont() {
		dao=new FqaDAO();
		System.out.println("------FqaCont()객체 생성 됨");
	}//end
	
	
	//http://localhost:9100/ans.do
	
	@RequestMapping("/fqa/ansForm.do")  //메인화면
	public ModelAndView ans() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("fqa/ansForm");
		
		
		mav.addObject("");
		return mav;
	}//ans() end
}//class end	

