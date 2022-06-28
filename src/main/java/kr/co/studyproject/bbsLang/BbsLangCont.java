package kr.co.studyproject.bbsLang;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;




@Controller
public class BbsLangCont {
	private static final int wgrpno = 0;
	BbsLangDAO dao=null;
	
	public BbsLangCont() {
		dao=new BbsLangDAO();
		System.out.println("-----BbsFreeCont() 객체 생성됨");
	}//end

@RequestMapping(value = "bbsLang/create.do", method = RequestMethod.GET)
public String createForm() {
	return "bbsLang/createForm";  // /WEB-INF/views/bbsFree/createForm.jsp
}//createForm() end



@RequestMapping(value = "bbsLang/create.do", method = RequestMethod.POST)
public ModelAndView createProc(@ModelAttribute BbsLangDTO dto) {
	                          //String title 써도 됨
	
	ModelAndView mav=new ModelAndView();
	mav.setViewName("bbsFree/msgView");
	
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
@RequestMapping("bbsLang/read.do")
public ModelAndView read() {
	ModelAndView mav=new ModelAndView();
	mav.setViewName("bbsLang/read");
	mav.addObject("list", dao.list());
	return mav;
}
@RequestMapping("bbsLang/delete.do")
public ModelAndView delete() {
	ModelAndView mav=new ModelAndView();
	mav.setViewName("bbsLang/deleteForm");
	//mav.addObject("list", dao.list(wgrpno));
	return mav;
}
@RequestMapping(value = "bbsLang/update.do", method = RequestMethod.GET)
public ModelAndView update_get() {
	ModelAndView mav=new ModelAndView();
	mav.setViewName("bbsLang/updateForm");
	//mav.addObject("list", dao.list(wgrpno));
	return mav;
}
@RequestMapping(value = "bbsLang/update.do", method = RequestMethod.POST)
public ModelAndView update() {
	ModelAndView mav=new ModelAndView();
	mav.setViewName("bbsLang/read");
	//mav.addObject("list", dao.list(wgrpno));
	return mav;
}
@RequestMapping("bbsLang/list.do")
public ModelAndView list() {
	ModelAndView mav=new ModelAndView();
	mav.setViewName("bbsLang/list");
	mav.addObject("list", dao.list());
	return mav;
}//list() end
}

