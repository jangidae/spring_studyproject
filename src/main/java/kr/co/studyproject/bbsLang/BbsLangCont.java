package kr.co.studyproject.bbsLang;

import java.util.Collections;
import java.util.List;

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
			System.out.println("-----BbsLangCont() 객체 생성됨");
		}//end
	
	@RequestMapping(value = "bbsLang/create.do", method = RequestMethod.GET)
	public String createForm() {
		return "bbsLang/createForm";  // /WEB-INF/views/bbsLang/createForm.jsp
	}//createForm() end
	
	
	
	@RequestMapping(value = "bbsLang/create.do", method = RequestMethod.POST)
	public ModelAndView createProc(@ModelAttribute BbsLangDTO dto, HttpServletRequest request) {
		                          
		
		ModelAndView mav=new ModelAndView();
		mav.setViewName("bbsLang/msgView");
		
		dto.setIp(request.getRemoteAddr());
		dto.setUserid("aa");  //(String)request.getSession().getAttribute("세션명")  
		int	cnt=dao.create(dto);
		
		if(cnt==0) {
	        String msg="<p>게시물 등록 실패</p>";
	        String img="<img src='../images/face-sad.png'>";
	        String link1="<input type='button' value='다시 시도' onclick='javascript:history.back()'>";
	        String link2="<input type='button' value='게시판 목록' onclick='location.href=\"list.do\"'>";
	        mav.addObject("msg", msg);
	        mav.addObject("img", img);
	        mav.addObject("link1", link1);
	        mav.addObject("link2", link2);
		}else {
	        String msg="<p>게시물 등록 성공</p>";
	        String img="<img src='../images/face-smile.png'>";
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
		int res = dao.delete(dto);
		if(res==0) {
			mav.setViewName("bbsLang/msgView2");
			  String msg="<p>비밀번호가 틀렸습니다</p>";
			  String img="<img src='../images/face-sad.png'>";
			  String link2="<input type='button' value='게시판 목록' onclick='location.href=\"list.do\"'>";
			  mav.addObject("msg", msg);
			  mav.addObject("img", img);
			  mav.addObject("link2", link2);
		}else {
			mav.setViewName("redirect:/bbsLang/list.do");
		}//if end
		return mav;
	}//delete() end
	
	@RequestMapping(value = "bbsLang/update.do", method = RequestMethod.GET)
	public ModelAndView update_get(@ModelAttribute BbsLangDTO dto) {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("bbsLang/updateForm");
		mav.addObject("update", dao.read(dto.getWno()));
		return mav;
	}//update_get() end
	
	@RequestMapping(value = "bbsLang/update.do", method = RequestMethod.POST)
	public ModelAndView update(@ModelAttribute BbsLangDTO dto) {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("bbsLang/read");
		dao.update(dto);
		mav.addObject("read", dao.read(dto.getWno()));
		return mav;
	}//update() end
	
	@RequestMapping("bbsLang/list.do")
	public ModelAndView list(HttpServletRequest req) {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("bbsLang/list");

		int totalRowCount=dao.totalRowCount(); //총 글갯수
	       
        //페이징
        int numPerPage   = 5;    // 한 페이지당 레코드 갯수
        int pagePerBlock = 5;   // 페이지 리스트
       
        String pageNum=req.getParameter("pageNum");
        if(pageNum==null){
              pageNum="1";
        }
       
        int currentPage=Integer.parseInt(pageNum);
        int startRow   =(currentPage-1)*numPerPage+1;
        int endRow     =currentPage*numPerPage;
       
        //페이지 수
        double totcnt = (double)totalRowCount/numPerPage;
        int totalPage = (int)Math.ceil(totcnt);
         
        double d_page = (double)currentPage/pagePerBlock;
        int Pages     = (int)Math.ceil(d_page)-1;
        int startPage = Pages*pagePerBlock;
        int endPage   = startPage+pagePerBlock+1;
       
       
        List list=null;     
        if(totalRowCount>0){           
              list=dao.list(startRow, endRow);          
        } else {           
              list=Collections.EMPTY_LIST;           
        }//if end
         
        int number=0;
        number=totalRowCount-(currentPage-1)*numPerPage;
         
        mav.addObject("number",    number);
        mav.addObject("pageNum",   currentPage);
        mav.addObject("startRow",  startRow);
        mav.addObject("endRow",    endRow);
        mav.addObject("count",     totalRowCount);
        mav.addObject("pageSize",  pagePerBlock);
        mav.addObject("totalPage", totalPage);
        mav.addObject("startPage", startPage);
        mav.addObject("endPage",   endPage);
        mav.addObject("list", list);
        return mav;
	}//list() end
	
	@RequestMapping(value="bbsLang/reply.do", method = RequestMethod.GET)
	public ModelAndView reply_create(@ModelAttribute BbsLangDTO dto) {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("bbsLang/reply");
		mav.addObject("read", dao.read(dto.getWno()));
		return mav;
	}//reply_create() end
	
	@RequestMapping(value="bbsLang/reply.do", method = RequestMethod.POST)
	public ModelAndView reply(@ModelAttribute BbsLangDTO dto, HttpServletRequest request) {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("bbsLang/reply");
		dto.setIp(request.getRemoteAddr());
		dto.setUserid("bb");  //(String)request.getSession().getAttribute("세션명")  
		dao.create(dto);
		mav.setViewName("redirect:/bbsLang/list.do");
		return mav;
	}//reply() end
	
	@RequestMapping("bbsLang/search.do")
	public ModelAndView search(HttpServletRequest request) {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("bbsLang/list");
		mav.addObject("list", dao.search(request.getParameter("search")));
		return mav;
	}//search() end
	
	

}//class end