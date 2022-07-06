package kr.co.studyproject.center;

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
public class CenterCont {

	CenterDAO dao=null;
	
	public CenterCont() {
		dao=new CenterDAO();
		System.out.println("------MycenterCont()객체 생성 됨");
	}//end
	
	
	//결과확인 http://localhost:9100/center/list.do
	//요청명령어 등록 후 실행의 주체는 메소드(함수)
	
	@RequestMapping(value = "center/create.do", method = RequestMethod.GET)
	public String createForm() {
		return "center/createForm";  // /WEB-INF/views/bbsFree/createForm.jsp
	}//createForm() end



	@RequestMapping(value = "/center/create.do", method = RequestMethod.POST)
	public ModelAndView createProc(@ModelAttribute CenterDTO dto) {
		                          //String title 써도 됨
		
		ModelAndView mav=new ModelAndView();
		mav.setViewName("center/msgView");
		
		int cnt=dao.create(dto);
		if(cnt==0) {
	        String msg="<p>게시물 등록 실패</p>";
	        String img="<img src='../images/fail.png'>";
	        String link1="<input type='button' value='다시 시도' onclick='javascript:history.back()'>";
	        String link2="<input type='button' value='게시판 목록' onclick='location.href=\"list.do\"'>";
	        mav.addObject("msg", msg);
	        mav.addObject("img", img);
	        mav.addObject("link1", link1);
	        mav.addObject("link2", link2);
		}else {
	        String msg="<p>게시물 등록 성공</p>";
	        String img="<img src='../images/sound.png'>";
	        String link1="<input type='button' value='계속 등록' onclick='location.href=\"create.do\"'>";
	        String link2="<input type='button' value='게시판 목록' onclick='location.href=\"list.do\"'>";
	        mav.addObject("msg", msg);
	        mav.addObject("img", img);
	        mav.addObject("link1", link1);
	        mav.addObject("link2", link2); 
		}//if end
		

		return mav;
		
	}//createProc() end
	@RequestMapping("center/read.do")
	public ModelAndView read() {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("center/read");
		mav.addObject("list", dao.list());
		return mav;
	}
	@RequestMapping("center/delete.do")
	public ModelAndView delete() {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("center/deleteForm");
		//mav.addObject("list", dao.list(wgrpno));
		return mav;
	}
	@RequestMapping(value = "center/update.do", method = RequestMethod.GET)
	public ModelAndView update_get() {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("center/updateForm");
		//mav.addObject("list", dao.list(wgrpno));
		return mav;
	}
	@RequestMapping(value = "center/update.do", method = RequestMethod.POST)
	public ModelAndView update() {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("center/read");
		//mav.addObject("list", dao.list(wgrpno));
		return mav;
	}
	@RequestMapping("center/list.do")
	public ModelAndView list() {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("center/list");
		mav.addObject("list", dao.list());
		return mav;
	}//list() end
	


	


	
	
	
}//class end
