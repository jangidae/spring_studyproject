package kr.co.studyproject.group;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	
	
	
	@RequestMapping(value = "studygroup/insert.do")
	public String SGinsert(){
		return "/studygroup/insert";
	}//SGinsert() end
	
	
	
	
	@RequestMapping(value = "studygroup/SGcreateProc.do", method = RequestMethod.POST)
	public ModelAndView joinproc(@ModelAttribute groupDTO dto, HttpServletRequest request) {
		
		//1) 사용자가 입력 요청한 값 가져오기
		//int    sgno          =Integer.parseInt(request.getParameter("sgno"));
		String sgname        =request.getParameter("sgname").trim();
		String sgleader      =request.getParameter("sgleader").trim();
		String sgintro       =request.getParameter("sgintro").trim();
		int    sgmaxuserno   =Integer.parseInt(request.getParameter("sgmaxuserno"));
		String sgselectlang  =request.getParameter("sgselectlang").trim();
		//String sgdate        =request.getParameter("sgdate").trim(); sysdate 입력하면 됩니다


		//2) dto 객체 담기
		dto.setSgname(sgname);
		dto.setSgleader(sgleader);
		dto.setSgintro(sgintro);
		dto.setSgmaxuserno(sgmaxuserno);
		dto.setSgselectlang(sgselectlang);
		
		
		//3) studyg테이블에 추가
		int cnt=dao.sgcreate(dto);
		
		ModelAndView mav=new ModelAndView();		
		
		if(cnt==0) {
			//그룹 생성 실패했을때
			mav.setViewName("member/msgView");//msgView로 이동
			mav.addObject("message", "<p>스터디그룹 생성에 실패하였습니다</p>");
			mav.addObject("link", "<a href='javascript:history.back()'>[다시시도]</a>");			
			
		}else {
			//그룹 생성 성공했을때
			mav.setViewName("member/msgView");//msgView로 이동
			mav.addObject("message", "<p>스터디그룹 생성에 성공하였습니다</p>");
			mav.addObject("link", "<a href='/home.do'>[메인]</a>");
		}//if end
		
		return mav;
		
	}//end
	
	@RequestMapping(value = "studygroup/myGroupList.do")
	public String myGroupList(Model model, HttpSession session) {
		
		String userId = (String)session.getAttribute("s_userid");
		
		List list = dao.myGroupList(userId);
		
		model.addAttribute("list", list);	
		
		return "/studygroup/myGroupList";
	}
	
	@RequestMapping(value = "studygroup/groupList.do")
	public String groupList(Model model, HttpSession session) {
		
		String userId = (String)session.getAttribute("s_userid");
		
		List list = dao.groupList(userId);
		
		model.addAttribute("list", list);	
		
		return "/studygroup/list";
	}
	
	@RequestMapping(value = "studygroup/detail.do")
	public String detail(Model model, HttpServletRequest request) {
		
		int sgno = Integer.parseInt(request.getParameter("sgno"));
				
		groupDTO dto = dao.groupDetail(sgno);
		
		model.addAttribute("dto", dto);	
		
		return "/studygroup/detail";
	}
	
	@RequestMapping(value = "studygroup/myGroupDetail.do")
	public String myGroupDetail(Model model, HttpServletRequest request) {
		
		int sgno = Integer.parseInt(request.getParameter("sgno"));
		String sglevel = request.getParameter("sglevel");
		
		// 그룹 정보
		groupDTO dto = dao.groupDetail(sgno);
		
		// 팀원 정보
		List list = dao.myGroupTeamMembers(sgno);
		
		model.addAttribute("dto", dto);	
		model.addAttribute("list", list);
		model.addAttribute("mySglevel", sglevel);
		
		return "/studygroup/myGroupDetail";
	}
	
	@RequestMapping(value = "studygroup/groupJoin.do")
	public ModelAndView groupJoin(Model model, HttpServletRequest request, HttpSession session) {
		
		int sgno = Integer.parseInt(request.getParameter("sgno"));
		String userId = (String)session.getAttribute("s_userid");
				
		int cnt = dao.groupJoin(sgno, userId);
		
		ModelAndView mav=new ModelAndView();	
		
		if(cnt==0) {
			//그룹 생성 실패했을때
			mav.setViewName("member/msgView");//msgView로 이동
			mav.addObject("message", "<p>스터디그룹 신청에 실패하였습니다</p>");
			mav.addObject("link", "<a href='javascript:history.back()'>[다시시도]</a>");			
			
		}else {
			//그룹 생성 성공했을때
			mav.setViewName("member/msgView");//msgView로 이동
			mav.addObject("message", "<p>스터디그룹 신청에 성공하였습니다</p>");
			mav.addObject("link", "<a href='/home.do'>[메인]</a>");
		}		
		return mav;
	}
	
	@RequestMapping(value = "studygroup/groupConfirm.do")
	public ModelAndView groupConfirm(Model model, HttpServletRequest request, HttpSession session) {
		
		int sgno = Integer.parseInt(request.getParameter("sgno"));
		String userId = request.getParameter("userid");
				
		int cnt = dao.groupConfirm(sgno, userId);
		
		ModelAndView mav=new ModelAndView();	
		
		if(cnt==0) {
			//그룹 생성 실패했을때
			mav.setViewName("member/msgView");//msgView로 이동
			mav.addObject("message", "<p>스터디그룹 승인에 실패하였습니다</p>");
			mav.addObject("link", "<a href='javascript:history.back()'>[다시시도]</a>");			
			
		}else {
			//그룹 생성 성공했을때
			mav.setViewName("member/msgView");//msgView로 이동
			mav.addObject("message", "<p>스터디그룹 승인에 성공하였습니다</p>");
			mav.addObject("link", "<a href='/home.do'>[메인]</a>");
		}		
		return mav;
	}
	
	/*
	 public ArrayList<groupDTO> list(){ //구인 테이블 리스트 
	      ArrayList<groupDTO> list=null;
	        try {
	            con=dbOpen.getConnection();
	            sql=new StringBuilder();
	            sql.append(" SELECT sgno, sgselectlang, sgname, sgintro, sgdate");
	            sql.append(" FROM studyg ");
	            sql.append(" ORDER BY wno DESC ");
	            pstmt=con.prepareStatement(sql.toString());
	            
	            rs=pstmt.executeQuery();
	            if(rs.next()) {
	                list=new ArrayList<groupDTO>();
	                do {
	                    groupDTO dto=new groupDTO();
	                    dto.setSgno(rs.getInt("sgno"));
	                    dto.setSgleader(rs.getString("sgselectlang"));
	                    dto.setSgname(rs.getString("sgname"));
	                    dto.setSgintro(rs.getString("sgintro")); 
	                    dto.setSgdate(rs.getString("sgdate"));
	                    list.add(dto); //list 저장
	                }while(rs.next());
	            }//if end
	        }catch(Exception e){
	            System.out.println("studygroup 목록 실패:" +e);
	         }finally{
	            DBClose.close(con, pstmt, rs);
	         }//리스트 end
	         return list;
	   }
	   
	   
	   public groupDTO read(int sgno) {
	        groupDTO dto=null;
	        try {
	            con=dbOpen.getConnection();
	            sql=new StringBuilder();
	            sql.append(" SELECT sgno, sgname, sgintro, sgmaxuserno, sgselectlang, sgdate ");
	            sql.append(" FROM studyg ");
	            sql.append(" WHERE sgno = ? ");
	            pstmt = con.prepareStatement(sql.toString());
	            pstmt.setInt(1, sgno);
	            
	            rs = pstmt.executeQuery();
	            if(rs.next()){
	                dto=new groupDTO();
	                dto.setSgno(rs.getInt("sgno"));
	                dto.setSgname(rs.getString("sgname"));
	                dto.setSgintro(rs.getString("sgintro"));
	                dto.setSgmaxuserno(rs.getInt("sgmaxuserno"));
	                dto.setSgselectlang(rs.getString("sgselectlang"));
	                dto.setSgdate(rs.getString("sgdate"));
	                
	            }//if end            
	        }catch(Exception e) {
	            System.out.println("스터디그룹 상세보기 실패: "+e);
	        }finally{
	            DBClose.close(con, pstmt, rs);
	        }//end    
	        return dto;
	    }//read() end
	   
	   */
	   
	
	
	
	
	
	
	
	
}//class end
