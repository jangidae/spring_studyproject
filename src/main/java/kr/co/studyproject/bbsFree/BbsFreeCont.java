package kr.co.studyproject.bbsFree;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class BbsFreeCont {
	
	BbsFreeDAO dao=null;
	
	public BbsFreeCont() {
		dao=new BbsFreeDAO();
		System.out.println("-----BbsFreeCont() 객체 생성됨");
	}//end


//결과확인 http://localhost:9100/bbsFree/create.do

@RequestMapping(value = "bbsFree/create.do", method = RequestMethod.GET)
public String createForm() {
	return "bbsFree/createForm";  // /WEB-INF/views/bbsFree/createForm.jsp
}//createForm() end



@RequestMapping(value = "bbsFree/create.do", method = RequestMethod.POST)
public ModelAndView createProc(@ModelAttribute BbsFreeDTO dto, HttpServletRequest request,MultipartFile file) {
	                          //String title 써도 됨
	String filename = file.getOriginalFilename();
	File target = new File("C://java202202//workspace_spring//spring_studyproject//src//main//webapp//storage",filename);
	try {
		FileCopyUtils.copy(file.getBytes(), target);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	ModelAndView mav=new ModelAndView();
	mav.setViewName("bbsFree/msgView");
	dto.setFilename(filename);
	dto.setFilesize(file.getSize());
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
@RequestMapping("bbsFree/read.do")
public ModelAndView read(@ModelAttribute BbsFreeDTO dto) {
	ModelAndView mav=new ModelAndView();
	mav.setViewName("bbsFree/read");
	dao.increment_view_count(dto.getWno());
	mav.addObject("read", dao.read(dto.getWno()));
	return mav;
}
@RequestMapping(value = "bbsFree/delete.do", method = RequestMethod.GET)
public ModelAndView delete_get(@ModelAttribute BbsFreeDTO dto) {
	ModelAndView mav=new ModelAndView();
	mav.setViewName("bbsFree/deleteForm");
	return mav;
}
@RequestMapping(value = "bbsFree/delete.do", method = RequestMethod.POST)
public ModelAndView delete(@ModelAttribute BbsFreeDTO dto) {
	ModelAndView mav=new ModelAndView();
	dao.delete(dto);
	mav.setViewName("redirect:/bbsFree/list.do");
	return mav;
}
@RequestMapping(value = "bbsFree/update.do", method = RequestMethod.GET)
public ModelAndView update_get(@ModelAttribute BbsFreeDTO dto) {
	ModelAndView mav=new ModelAndView();
	mav.setViewName("bbsFree/updateForm");
	mav.addObject("update", dao.read(dto.getWno()));
	return mav;
}
@RequestMapping(value = "bbsFree/update.do", method = RequestMethod.POST)
public ModelAndView update(@ModelAttribute BbsFreeDTO dto,MultipartFile file , HttpServletRequest request) {
	
	
	String filename = null;
	long filesize=0;
	if(file!=null) {
		filename = file.getOriginalFilename();
		filesize=file.getSize();
		File target = new File("C://java202202//workspace_spring//spring_studyproject//src//main//webapp//storage",filename);
		try {
			FileCopyUtils.copy(file.getBytes(), target);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	filename = request.getParameter("tmpfile");
	filesize = Long.parseLong(request.getParameter("tmpsize"));
	
	ModelAndView mav=new ModelAndView();
	mav.setViewName("bbsFree/read");
	dto.setFilename(filename);
	dto.setFilesize(filesize);
	
	dao.update(dto);
	mav.addObject("read", dao.read(dto.getWno()));
	return mav;
}

@RequestMapping("bbsFree/list.do")
public ModelAndView list() {
	ModelAndView mav=new ModelAndView();
	mav.setViewName("bbsFree/list");
	mav.addObject("list", dao.list());
	return mav;
}//list() end

@RequestMapping(value="bbsFree/reply.do", method = RequestMethod.GET)
public ModelAndView reply_create(@ModelAttribute BbsFreeDTO dto) {
	ModelAndView mav=new ModelAndView();
	mav.setViewName("bbsFree/reply");
	mav.addObject("read", dao.read(dto.getWno()));
	return mav;
}
@RequestMapping(value="bbsFree/reply.do", method = RequestMethod.POST)
public ModelAndView reply(@ModelAttribute BbsFreeDTO dto, HttpServletRequest request,MultipartFile file) {
	String filename = file.getOriginalFilename();
	File target = new File("C://java202202//workspace_spring//spring_studyproject//src//main//webapp//storage",filename);
	try {
		FileCopyUtils.copy(file.getBytes(), target);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	ModelAndView mav=new ModelAndView();
	mav.setViewName("bbsFree/reply");
	dto.setFilename(filename);
	dto.setFilesize(file.getSize());
	dto.setIp(request.getRemoteAddr());
	dto.setUserid("bb");  //(String)request.getSession().getAttribute("세션명")  
	dao.create(dto);
	mav.setViewName("redirect:/bbsFree/list.do");
	return mav;
}
@RequestMapping("bbsFree/search.do")
public ModelAndView search(HttpServletRequest request) {
	ModelAndView mav=new ModelAndView();
	mav.setViewName("bbsFree/list");
	mav.addObject("list", dao.search(request.getParameter("search")));
	return mav;
}

}