package kr.co.studyproject.notice;

import java.util.Collections;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.co.studyproject.notice.NoticeDTO;
import net.utility.UploadSaveManager;

@Controller
public class NoticeController {
	
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
	public ModelAndView createProc(@ModelAttribute NoticeDTO dto, HttpServletRequest req) {
		                         
		
		ModelAndView mav=new ModelAndView();
		mav.setViewName("Notice/msgView");
		
		/////////////////////////////////////////////////////
		//첨부된 파일 처리
				//->실제 파일은 /storage폴더에 저장
				//->저장된 파일 관련 정보는 media테이블에 저장
				
				//파일 저장 폴더의 실제 물리적인 경로 가져오기
				String basePath=req.getRealPath("/storage");
				
				//1)<input type='file' name='posterMF' size='50'>
				MultipartFile posterMF=dto.getPosterMF();//파일 가져오기
				// /storage폴더에 파일 저장하고, 리네임된 파일명 반환
				String poster=UploadSaveManager.saveFileSpring30(posterMF, basePath);
				dto.setPoster(poster);//리네임된 파일명을 dto 객체 담기
				
				//2)<input type='file' name='filenameMF'>
				MultipartFile filenameMF=dto.getFilenameMF();
				String filename=UploadSaveManager.saveFileSpring30(filenameMF, basePath);
				dto.setFilename(filename);
				dto.setFilesize(filenameMF.getSize());
		///////////////////////////////////////////////////////
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
	public ModelAndView delete(@ModelAttribute NoticeDTO dto, HttpServletRequest req) {
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
			//String basePath=req.getRealPath("/storage");
            //UploadSaveManager.deleteFile(basePath, oldDTO.getPoster());
            //UploadSaveManager.deleteFile(basePath, oldDTO.getFilename());
		}//if end
		return mav;
	}//delete() end
	
	@RequestMapping(value = "Notice/noticeUpdate.do", method = RequestMethod.GET)
	public ModelAndView update_get(@ModelAttribute NoticeDTO dto, HttpServletRequest req) {
		int wno=Integer.parseInt(req.getParameter("wno"));		
		ModelAndView mav=new ModelAndView();
		mav.setViewName("Notice/noticeUpdate");
		mav.addObject("read", dao.read(dto.getWno()));
		//mav.addObject("list", dao.list(wno));
		return mav;
	}
	
	@RequestMapping(value = "Notice/noticeUpdate.do", method = RequestMethod.POST)
	public ModelAndView update(@ModelAttribute NoticeDTO dto) {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("Notice/noticeRead");
		dao.update(dto);
		mav.addObject("read", dao.read(dto.getWno()));
		return mav;
	}//update() end
	
	/*
	@RequestMapping("Notice/noticeList.do")
	public ModelAndView list() {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("Notice/noticeList");
		mav.addObject("list", dao.list());
		return mav;
	}//list() end*/
	
	/*
	//첨부된 파일 처리
			//->실제 파일은 /storage폴더에 저장
			//->저장된 파일 관련 정보는 media테이블에 저장
			
			//파일 저장 폴더의 실제 물리적인 경로 가져오기
			String basePath=req.getRealPath("/storage");
			
			//1)<input type='file' name='posterMF' size='50'>
			MultipartFile posterMF=dto.getPosterMF();//파일 가져오기
			// /storage폴더에 파일 저장하고, 리네임된 파일명 반환
			String poster=UploadSaveManager.saveFileSpring30(posterMF, basePath);
			dto.setPoster(poster);//리네임된 파일명을 dto 객체 담기
			
			//2)<input type='file' name='filenameMF'>
			MultipartFile filenameMF=dto.getFilenameMF();
			String filename=UploadSaveManager.saveFileSpring30(filenameMF, basePath);
			dto.setFilename(filename);
			dto.setFilesize(filenameMF.getSize());	*/
	

    @RequestMapping("Notice/noticeList.do")
    public ModelAndView list(HttpServletRequest req) {
        ModelAndView mav=new ModelAndView();
        mav.setViewName("Notice/noticeList");
       
        int totalRowCount=dao.totalRowCount(); //총 글갯수
       
        //페이징
        int numPerPage   = 5;    // 한 페이지당 레코드 갯수
        int pagePerBlock = 10;   // 페이지 리스트
       
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
              list=dao.list2(startRow, endRow);          
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
    
    
    
    @RequestMapping("Notice/search.do")
    public ModelAndView search(HttpServletRequest request) {
       ModelAndView mav = new ModelAndView();
       //mav.setViewName("Notice/list"); 뷰페이지명 noticeList.jsp입니다
       mav.setViewName("Notice/noticeList");
       mav.addObject("list", dao.search(request.getParameter("search")));
       return mav;
    }//search() end
    
    
    
    
    
    
    
}

