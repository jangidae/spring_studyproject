package kr.co.studyproject.mypage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;



//URL에서 요청한 명령어를 읽어서 실행해 주는 클래스
//HelloController클래스는 컨트롤러 가능하다
//스프링컨테이너(웹서버)가 자동으로 객체 생성된다
@Controller
public class MypageCont {

	MypageDAO dao=null;
	
	public MypageCont() {
		dao=new MypageDAO();
		System.out.println("------MypageCont()객체 생성 됨");
	}//end
	
	
	//결과확인 http://localhost:9100/mypage/pageform.do
	//요청명령어 등록 후 실행의 주체는 메소드(함수)
	
	@RequestMapping("/mypage/pageindex.do")
	public String pageform() {
	
			
		return "mypage/pageIndex";
	}//pageform() end
	
	@RequestMapping("mypage/list.do")
	public ModelAndView list() {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("mypage/pageIndex");
		mav.addObject("list", dao.list());
		return mav;
	}//list() end
	
	//게시글 작성화면
	@RequestMapping(value = "/write.do", method = RequestMethod.GET)
	public String write() {
		return "mypage/write"; //wrtie.jsp이동
	}
	

	


	
	
	
}//class end
