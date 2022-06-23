package kr.co.studyproject.Member;

import javax.mail.*;
import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.utility.DBOpen;



@Controller
public class MemberCont {
	
	MemberDAO dao=null;
	
	//findID()
	private DBOpen dbopen=null;
	private Connection con=null;
    private PreparedStatement pstmt=null;
    private ResultSet rs=null;
    private StringBuilder sql=null;
	
	public MemberCont() {
		dao=new MemberDAO(); //DB연결 객체 생성
		System.out.println("-----MemberCont() 객체 생성됨");
	}//end
	
	@RequestMapping("/member/loginform.do")
	public String loginform () {
		return "member/loginForm";
	}//
	
	
	
	@RequestMapping(value = "/member/loginproc.do", method = RequestMethod.POST)
	public ModelAndView loginproc(@ModelAttribute MemberDTO dto, HttpSession session) {
		
		ModelAndView mav=new ModelAndView();
		
		//String userid = dto.getUserid();
		//String upw    = dto.getUpw();
		
		//오라클 DB에서 로그인 성공하면 회원등급을 가져오고, 실패하면 null값을 가져옴
		String ulevel=dao.loginProc(dto);
		
		if(ulevel==null) {
			//로그인 실패했을때
			mav.setViewName("member/msgView");//msgView로 이동
			mav.addObject("message", "<p>아이디와 비번이 일치하지 않습니다</p>");
			mav.addObject("link", "<a href='javascript:history.back()'>[다시시도]</a>");			
			
		}else {
			//로그인 성공했을때
			session.setAttribute("s_userid", dto.getUserid());
			session.setAttribute("s_upw", dto.getUpw());
			session.setAttribute("s_ulevel", ulevel);//38행 참조
			mav.setViewName("redirect:/home.do");			
		}//if end		
		
		return mav;
		
	}//end
	
	
	
	@RequestMapping("/member/logout.do")
	public String logout(HttpSession session) {
		
		//세션변수 제거 -> 결과값 null(변수 자체가 존재하지 않게 됨)
		session.removeAttribute("s_userid");
		session.removeAttribute("s_upw");
		session.removeAttribute("s_ulevel");
		
		//페이지 이동
		return "redirect:/home.do";
		
		//로그아웃하면 세션변수 null값 됨
	} //logout() end
	
	
	
	@RequestMapping(value = "/findIDform.do")
	public String findIDform() throws Exception{
		return "/member/findIDform";
	}//findIDform() end
	
	
	@RequestMapping(value = "/findID.do")
	public void findID() {
		
	}//
	
	
	@RequestMapping(value = "/agreement.do")
	public String agreement() throws Exception{
		return "/member/agreement";
	}//agreement() end
	
	
	@RequestMapping(value = "joinform.do")
	public String joinform() throws Exception{
		return "/member/joinform";
	}//joinform() end
	
	@RequestMapping(value = "emailCheck.do")
	public String emailCheck() throws Exception{
		return "/member/emailCheckForm.jsp";
	}//emailCheck() end
	
	
	
}//end
