package kr.co.studyproject.bbsLang;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;




@Controller
public class BbsLangCont {
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
public ModelAndView createProc(@ModelAttribute BbsLangDTO dto, HttpServletRequest request) {
	                         
	
	ModelAndView mav=new ModelAndView();
	mav.setViewName("bbsLang/msgView");
	dto.setIp(request.getRemoteAddr());
	dto.setUserid("aa");  //(String)request.getSession().getAttribute("세션명")  
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
public ModelAndView read(@ModelAttribute BbsLangDTO dto) {
	ModelAndView mav=new ModelAndView();
	mav.setViewName("bbsLang/read");
	dao.increment_view_count(dto.getWno());
	mav.addObject("read", dao.read(dto.getWno()));
	return mav;
}//read() end

@RequestMapping(value = "bbsLang/delete.do", method = RequestMethod.GET)
public ModelAndView delete_get(@ModelAttribute BbsLangDTO dto) {
	ModelAndView mav=new ModelAndView();
	mav.setViewName("bbsLang/deleteForm");
	return mav;
}//delete_get() end

@RequestMapping(value = "bbsLang/delete.do", method = RequestMethod.POST)
public ModelAndView delete(@ModelAttribute BbsLangDTO dto) {
	ModelAndView mav=new ModelAndView();
	dao.delete(dto);
	mav.setViewName("redirect:/bbsLang/list.do");
	return mav;
}//delete() end

@RequestMapping(value = "bbsLang/update.do", method = RequestMethod.GET)
public ModelAndView update_get(@ModelAttribute BbsLangDTO dto) {
	ModelAndView mav=new ModelAndView();
	mav.setViewName("bbsLang/updateForm");
	mav.addObject("update", dao.read(dto.getWno()));
	return mav;
}//update_get()end

@RequestMapping(value = "bbsLang/update.do", method = RequestMethod.POST)
public ModelAndView update(@ModelAttribute BbsLangDTO dto) {
	ModelAndView mav=new ModelAndView();
	mav.setViewName("bbsLang/read");
	dao.update(dto);
	mav.addObject("read", dao.read(dto.getWno()));
	return mav;
}//update() end

@RequestMapping("bbsLang/list.do")
public ModelAndView list() {
	ModelAndView mav=new ModelAndView();
	mav.setViewName("bbsLang/list");
	mav.addObject("list", dao.list());
	return mav;
}//list() end



}//class end