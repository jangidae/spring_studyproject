package kr.co.studyproject.notice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NoticeController {
	private static final int wno = 0;
	NoticeDAO dao=null;
	
	public NoticeController() {
		dao=new NoticeDAO();
		System.out.println("----------notice 객체 생성");
	}//end




	@RequestMapping(value = "Notice/create.do", method = RequestMethod.GET)
	public String createForm() {
		return "Notice/noticeForm";  // /WEB-INF/views/Notice/noticeForm.jsp
	}//createForm() end
	
	
	
	@RequestMapping(value = "Notice/create.do", method = RequestMethod.POST)
	public ModelAndView createProc(@ModelAttribute NoticeDTO dto) {
		                         
		
		ModelAndView mav=new ModelAndView();
		mav.setViewName("Notice/msgView");
		
		int cnt=dao.create(dto);
		if(cnt==0) {
	        String msg="<p>게시물 등록 실패</p>";
	        String img="<img src='../images/fail.png'>";
	        String link1="<input type='button' value='다시 시도' onclick='javascript:history.back()'>";
	        String link2="<input type='button' value='게시판 목록' onclick='location.href=\"/Notice/noticeList.do\"'>";
	        mav.addObject("msg", msg);
	        mav.addObject("img", img);
	        mav.addObject("link1", link1);
	        mav.addObject("link2", link2);
		}else {
	        String msg="<p>게시물 등록 성공</p>";
	        String img="<img src='../images/sound.png'>";
	        String link1="<input type='button' value='계속 등록' onclick='location.href=\"/Notice/create.do\"'>";
	        String link2="<input type='button' value='게시판 목록' onclick='location.href=\"/Notice/noticeList.do\"'>";
	        mav.addObject("msg", msg);
	        mav.addObject("img", img);
	        mav.addObject("link1", link1);
	        mav.addObject("link2", link2); 
		}//if end
		
		return mav;
		
	}//createProc() end
	
	
	//noticeList.jsp에서 <a href="/Notice/noticeRead.do?wno 참조	
	@RequestMapping("Notice/noticeRead.do")
	public ModelAndView read(HttpServletRequest req) {
		int wno=Integer.parseInt(req.getParameter("wno")); //?뒤에 wno변수에 글번호를 넘깁니다
		ModelAndView mav=new ModelAndView();
		mav.setViewName("Notice/noticeRead");
		mav.addObject("read", dao.read(wno));
		
		
		/* noticeRead.jsp에서 문하빈님은 read라는 변수를 사용했습니다. (아래소스참조)
		  <tr>
	        <th>제목</th>
	        <td>${read.wtitle}</td>
	    </tr>		
		*/
		return mav;
	}
	
  /*	@RequestMapping(value = "Notice/noticeDel.do", method = RequestMethod.POST)
	public ModelAndView delete(@ModelAttribute NoticeDTO dto) {
		ModelAndView mav=new ModelAndView();
		int res = dao.delete(dto);  
		if(res==0) {
			mav.setViewName("Notice/msgView");
			  String msg="<p>게시글을 삭제할 수 없습니다.</p>";
			  String link="<input type='button' value='게시판 목록' onclick='location.href=\"noticeList.do\"'>";
			  mav.addObject("msg", msg);
			  mav.addObject("link", link);
		}else {
			mav.setViewName("redirect:/Notice/noticeDel.do");
		}//if end
		return mav;
	}//delete() end */
	
	
	//noticeRead.jsp에서 
	@RequestMapping(value = "Notice/noticeDel.do", method = RequestMethod.GET)
	public ModelAndView delete_get(HttpServletRequest req) {
		//삭제하고자 하는 글번호를 가져옵니다
		//<input type='button' value='삭제' onclick="location.href='/Notice/noticeDel.do?wno=${read.wno}';">
		int wno=Integer.parseInt(req.getParameter("wno"));		
		ModelAndView mav=new ModelAndView();
		mav.setViewName("Notice/noticeDel");
		mav.addObject("wno", wno); //삭제하고자 하는 글번호를 request영역으로 올립니다
		return mav;
	}//delete_get() end

	@RequestMapping(value = "Notice/noticeDel.do", method = RequestMethod.POST)
	public ModelAndView delete(@ModelAttribute NoticeDTO dto) {
		ModelAndView mav=new ModelAndView();
		int res = dao.delete(dto);
		if(res==0) {
			mav.setViewName("Notice/msgView");
			  String msg="<p>비밀번호가 틀렸습니다</p>";
			  String link="<input type='button' value='게시판 목록' onclick='location.href=\"noticeList.do\"'>";
			  mav.addObject("msg", msg);
			  mav.addObject("link", link);
		}else {
			mav.setViewName("redirect:/Notice/noticeList.do");
		}//if end
		return mav;
	}//delete() end
	
	@RequestMapping(value = "Notice/noticeUpdate.do", method = RequestMethod.GET)
	public ModelAndView update_get() {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("Notice/updateForm");
		//mav.addObject("list", dao.list(wno));
		return mav;
	}
	@RequestMapping(value = "Notice/noticeUpdate.do", method = RequestMethod.POST)
	public ModelAndView update() {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("Notice/noticeRead");
		//mav.addObject("list", dao.list(wno));
		return mav;
	}
	
	@RequestMapping("Notice/noticeList.do")
	public ModelAndView list() {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("Notice/noticeList");
		mav.addObject("list", dao.list());
		return mav;
	}//list() end
}

