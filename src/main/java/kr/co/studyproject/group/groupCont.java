package kr.co.studyproject.group;



import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class groupCont {
	
	groupDAO dao=null;
	
	public groupCont() {
		dao=new groupDAO();	//DB연결 객체 생성
		System.out.println("----groupCont() 객체 생성");
	}//end
	
	// 결과확인 http://localhost:9100/studygroup/create.do
	
	@RequestMapping(value = "studygroup/create.do", method = RequestMethod.GET)
	public String createForm() {
		return "studygroup/createForm";	// /WEB-INF/views/studygroup/createForm.jsp
	}//createForm() end
	
	@RequestMapping(value = "studygroup/create.do", method = RequestMethod.POST)
		public ModelAndView createProc(@ModelAttribute groupDTO dto) {
									//String title 사용해도 괜찮다
			ModelAndView mav=new ModelAndView();
			mav.setViewName("studygroup/msgView");
			
			int cnt=dao.create(dto);
			if(cnt==0) {
				String msg="<p>스터디 그룹 등록 실패</P>";
				String img="<img src='../images/fail.png'>";
				String link1="<input type='button' value='다시시도' onclick='javascript:history.back()'>";
	            String link2="<input type='button' value='그룹목록' onclick='location.href=\"list.do\"'>";
	            
	            mav.addObject("msg", msg);
	            mav.addObject("img", img);
	            mav.addObject("link1", link1);
	            mav.addObject("link2", link2);
			}else {
				String msg="<p>스터디 그룹 등록 성공</P>";
				String img="<img src='../images/sound.png'>";
				String link1="<input type='button' value='다시시도' onclick='javascript:history.back()'>";
	            String link2="<input type='button' value='그룹목록' onclick='location.href=\"list.do\"'>";
	            
	            mav.addObject("msg", msg);
	            mav.addObject("img", img);
	            mav.addObject("link1", link1);
	            mav.addObject("link2", link2);
			}//if end
				return mav;
	}//createProc() end
	
	
	@RequestMapping(value = "studygroup/delete.do", method = RequestMethod.GET)
	public ModelAndView deleteForm(int wno) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("studygroup/deleteForm");
		mav.addObject("studygroup",wno);
		return mav;
	}//deleteForm() end
	
	@RequestMapping(value = "studygroup/delete.do", method = RequestMethod.POST)
	public ModelAndView deleteProc(int wno) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("studygroup/msgView");
		
		int cnt=dao.delete(wno);
		if(cnt==0) {
			String msg="<p>스터디 그룹 삭제 실패</p>";
            String img="<img src='../images/angel.png'>";
            String link1="<input type='button' value='다시시도' onclick='javascript:history.back()'>";
            String link2="<input type='button' value='그룹목록' onclick='location.href=\"list.do\"'>";
            mav.addObject("msg", msg);
            mav.addObject("img", img);
            mav.addObject("link1", link1);
            mav.addObject("link2", link2);
		}else {
			String msg="<p>스터디 그룹 삭제 성공</p>";
            String img="<img src='../images/yellowstar.png'>";
            String link2="<input type='button' value='그룹목록' onclick='location.href=\"list.do\"'>";
            mav.addObject("msg", msg);
            mav.addObject("img", img);
            mav.addObject("link2", link2);            
		}//if end
		return mav;
	}//deleteProc() end
	
	@RequestMapping(value = "studygroup/update.do", method = RequestMethod.GET)
	public ModelAndView updateForm(int wno) {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("studygroup/updateForm");
		mav.addObject("dto", dao.read(wno));
	

		return mav;
	}//updateForm() end
	
	@RequestMapping(value = "studygroup/update.do", method = RequestMethod.POST)
	public ModelAndView updateProc(HttpServletRequest req) { 
		
		int wno=Integer.parseInt(req.getParameter("wno"));
        String title=req.getParameter("title").trim();
        
        groupDTO dto=new groupDTO();
        dto.setWno(wno);
        dto.setTitle(title);
        
        ModelAndView mav=new ModelAndView();
        mav.setViewName("studygroup/msgView");
        
        int cnt=dao.update(dto);
		if(cnt==0) {
			String msg="<p>스터디 그룹 수정 실패</p>";
            String img="<img src='../images/fail.png'>";
            String link1="<input type='button' value='다시시도' onclick='javascript:history.back()'>";
            String link2="<input type='button' value='그룹목록' onclick='location.href=\"list.do\"'>";
            mav.addObject("msg", msg);
            mav.addObject("img", img);
            mav.addObject("link1", link1);
            mav.addObject("link2", link2);
		}else {
			String msg="<p>스터디 그룹 수정 성공</p>";
            String img="<img src='../images/sound.png'>";
            String link2="<input type='button' value='그룹목록' onclick='location.href=\"list.do\"'>";
            mav.addObject("msg", msg);
            mav.addObject("img", img);
            mav.addObject("link2", link2);            
		}//if end
        
        return mav;		
	}//updateProc() end
// 	리스트 
	@RequestMapping("studygroup/list.do")
		public ModelAndView list() {
			ModelAndView mav= new ModelAndView();
			mav.setViewName("studygroup/list");
			mav.addObject("list", dao.list());
		
			return mav;
	}// list() end
	
}//class end
