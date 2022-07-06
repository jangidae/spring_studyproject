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
	
	
	@RequestMapping(value = "/findIDProc.do")
	public ModelAndView findIDProc(@ModelAttribute MemberDTO dto, HttpServletRequest request) {
		
		String uname=request.getParameter("uname").trim();
		String email=request.getParameter("email").trim();
		dto.setUname(uname);
		dto.setEmail(email);
		
		
		boolean flag=dao.findID(dto); 
		ModelAndView mav=new ModelAndView();
		  if(flag==false) {
				//로그인 실패했을때
				mav.setViewName("member/msgView");//msgView로 이동
				mav.addObject("message", "<p>이름/이메일을 다시 한 번 확인해주세요</p>");
				mav.addObject("link", "<a href='javascript:history.back()'>[다시시도]</a>");			
				
			}else {
				//가입 성공했을때
				mav.setViewName("member/msgView");//msgView로 이동
				mav.addObject("message", "<p>아이디/임시 비밀번호가 이메일로 전송되었습니다</p>");
				mav.addObject("link", "<a href='../home.do'>[메인]</a>");
			}//if end
		 
		  return mav;
		
	}//findIDProc() end
	
	
	
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
	
	
	@RequestMapping(value = "joinproc.do", method = RequestMethod.POST)
	public ModelAndView joinproc(@ModelAttribute MemberDTO dto, HttpSession session, HttpServletRequest request) {
		
		//1) 사용자가 입력 요청한 값 가져오기
		//String userid =(String)session.getAttribute("s_userid");
		String userid =request.getParameter("userid").trim();
		String upw    =request.getParameter("upw").trim();
		String uname  =request.getParameter("uname").trim();
		String email  =request.getParameter("email").trim();
		String phnum  =request.getParameter("phnum").trim();
		String introd =request.getParameter("introd").trim();


		//2) dto 객체 담기
		dto.setUserid(userid);
		dto.setUpw(upw);
		dto.setUname(uname);
		dto.setEmail(email);
		dto.setPhnum(phnum);
		dto.setIntrod(introd);

		//3) member테이블에 추가
		int cnt=dao.create(dto);
		
		ModelAndView mav=new ModelAndView();		
		
		if(cnt==0) {
			//로그인 실패했을때
			mav.setViewName("member/msgView");//msgView로 이동
			mav.addObject("message", "<p>회원가입에 실패하였습니다</p>");
			mav.addObject("link", "<a href='javascript:history.back()'>[다시시도]</a>");			
			
		}else {
			//가입 성공했을때
			mav.setViewName("member/msgView");//msgView로 이동
			mav.addObject("message", "<p>회원가입에 성공하였습니다</p>");
			mav.addObject("link", "<a href='./member/loginform.do'>[로그인]</a>");
		}//if end
		
		return mav;
		
	}//end
	
	
	
	@RequestMapping(value = "/idCheckForm.do")
	public String idCheckForm() throws Exception{
		return "/member/idCheckForm";
	}//idCheckForm() end	
	
	

	@RequestMapping(value = "/member/idCheckproc.do", method = RequestMethod.POST)
	public ModelAndView idCheckproc(HttpServletRequest request) {
		
		String userid=request.getParameter("userid").trim();//사용자가 입력한 아이디 가져오기
		int cnt=dao.duplecateID(userid);
		ModelAndView mav=new ModelAndView();
		
			if(cnt==0) { 
				//사용가능한 아이디일때
				mav.setViewName("member/msgView1");//msgView로 이동
				
				//mav.addObject("script") 변수가 많이 중복되어 있습니다
				//mav.addObject("script", "<script>");
				//mav.addObject("script", "function apply(userid) {opener.document.joinfrm.userid.value=userid; window.close();}");
				//mav.addObject("script", "</script>");
				
				String script="";	//여기에 출력하고자 하는 값을 전부 넣음
				script+="<script>";
				script+="  function apply(userid) {opener.document.joinfrm.userid.value=userid; window.close();}";
				script+="</script>";
				
				mav.addObject("script", script);
				mav.addObject("message1", "입력ID : <strong>" + userid + "</strong>");
				mav.addObject("message2", "<p>사용 가능한 아이디입니다</p>");
				mav.addObject("link1", "<a href=\"javascript:apply('" + userid + "')\">[적용]</a>");					
				mav.addObject("link2", "<a href='javascript:window.close()'>[창닫기]</a>");
				
			}else {
				//사용불가능한 아이디일때
				mav.setViewName("member/msgView2");//msgView로 이동
				
				mav.addObject("message1", "<p style='color:red'>해당 아이디는 사용할 수 없습니다</p>");
				mav.addObject("link1", "<a href='javascript:history.back()'>[다시검색]</a>");
				mav.addObject("link2", "<a href='javascript:window.close()'>[창닫기]</a>");
			}//if end
			

			
			return mav;
		
		
		
	}//idCheckProc() end
		
	
	
	@RequestMapping(value = "/emailCheckForm.do")
	public String emailCheckForm() throws Exception{
		return "/member/emailCheckForm";
	}//emailCheckForm() end
	
	
	@RequestMapping(value = "/member/emailCheckproc.do", method = RequestMethod.POST)
	public ModelAndView emailCheckproc(HttpServletRequest request) {
		
		String email=request.getParameter("email").trim();//사용자가 입력한 이메일 가져오기
		int cnt=dao.duplecateemail(email);
		ModelAndView mav=new ModelAndView();
		
			if(cnt==0) { 
				//사용가능한 이메일일때
				mav.setViewName("member/msgView1");//msgView로 이동
				
				//mav.addObject("script") 변수가 많이 중복되어 있습니다
				//mav.addObject("script", "<script>");
				//mav.addObject("script", "function apply(email) {opener.document.joinfrm.email.value=email; window.close();}");
				//mav.addObject("script", "</script>");
				
				String script="";	//여기에 출력하고자 하는 값을 전부 넣음
				script+="<script>";
				script+="  function apply(email) {opener.document.joinfrm.email.value=email; window.close();}";
				script+="</script>";
				
				mav.addObject("script", script);
				mav.addObject("message1", "입력 e-mail : <strong>" + email + "</strong>");
				mav.addObject("message2", "<p>사용 가능한 이메일입니다</p>");
				mav.addObject("link1", "<a href=\"javascript:apply('" + email + "')\">[적용]</a>");					
				mav.addObject("link2", "<a href='javascript:window.close()'>[창닫기]</a>");
				
			}else {
				//사용불가능한 이메일일때
				mav.setViewName("member/msgView2");//msgView로 이동
				
				mav.addObject("message1", "<p style='color:red'>해당 이메일은 사용할 수 없습니다</p>");
				mav.addObject("link1", "<a href='javascript:history.back()'>[다시검색]</a>");
				mav.addObject("link2", "<a href='javascript:window.close()'>[창닫기]</a>");
			}//if end

			
			return mav;
		
		
	}//idCheckProc() end
	
	
	
	
	@RequestMapping(value = "/member/memberModifyForm.do")
	public String memberModifyForm() throws Exception{
		return "/member/memberModifyForm";
	}//memberModifyForm() end	
	
	
	
	@RequestMapping(value = "/member/memberModifyProc.do", method = RequestMethod.POST)
	public ModelAndView memberModifyProc(@ModelAttribute MemberDTO dto, HttpSession session, HttpServletRequest request) {
		
		String upw    =request.getParameter("upw").trim();
		String uname  =request.getParameter("uname").trim();
		String phnum  =request.getParameter("phnum").trim();
		String email  =request.getParameter("email").trim();
		String introd =request.getParameter("introd").trim();

		dto.setUserid((String)session.getAttribute("s_userid"));//세션아이디(로그인유지중인 그 아이디) 가져오기
		dto.setUpw(upw);
		dto.setUname(uname);
		dto.setEmail(email);
		dto.setPhnum(phnum);
		dto.setIntrod(introd);
		
		int cnt=dao.modifyProc(dto);
		ModelAndView mav=new ModelAndView();
	    
	    
	    if(cnt==0) {
			//로그인 실패했을때
			mav.setViewName("member/msgView");//msgView로 이동
			mav.addObject("message", "<p>회원정보 수정에 실패했습니다</p>");
			mav.addObject("link", "<a href='javascript:history.back()'>[다시시도]</a>");			
			
		}else {
			//가입 성공했을때
			mav.setViewName("member/msgView");//msgView로 이동
			mav.addObject("message", "<p>회원정보가 수정되었습니다</p>");
			mav.addObject("link", "<a href='../home.do'>[메인]</a>");
		}//if end
			
			return mav;
		
	}//memberModifyProc() end
	
	
	
	
	@RequestMapping(value = "/member/memberWithdrawForm.do")
	public String memberWithdrawForm() throws Exception{
		return "/member/memberWithdrawForm";
	}//memberWithdrawForm() end	
	
	
	
	@RequestMapping(value = "/member/memberWithdraw.do", method = RequestMethod.POST)
	public ModelAndView memberWithdraw(@ModelAttribute MemberDTO dto, MemberDAO dao, HttpSession session, HttpServletRequest request) {
		
		String userid=request.getParameter("userid");
		String upw =request.getParameter("upw").trim();

		dto.setUserid(userid);
		dto.setUpw(upw);

		
		int cnt=dao.delete(dto);
		ModelAndView mav=new ModelAndView();
	    
	    
	    if(cnt==0) {
			//탈퇴 실패했을때
			mav.setViewName("member/msgView");//msgView로 이동
			mav.addObject("message", "<p>비밀번호가 일치하지 않습니다</p>");
			mav.addObject("link", "<a href='javascript:history.back()'>[다시시도]</a>");			
			
		}else {
			//탈퇴 성공했을때
			mav.setViewName("member/msgView");//msgView로 이동
			mav.addObject("message", "<p>계정 탈퇴에 성공했습니다</p>");
			mav.addObject("link", "<a href='../home.do'>[메인]</a>");
		}//if end
	    
	    
	   //세션변수 제거
	  	session.removeAttribute("s_userid");
	  	session.removeAttribute("s_upw");
	  	session.removeAttribute("s_ulevel");
	  	
	  	
	  	return mav;
		
	}//memberWithdraw() end
	
	
	
	
	
}//end
