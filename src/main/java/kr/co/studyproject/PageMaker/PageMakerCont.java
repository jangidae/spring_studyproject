package kr.co.studyproject.PageMaker;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.utility.Criteria;



@Controller

@RequestMapping(value="/bbsFree/bbsFreelist.do")
public ModelAndView openBoardList(Criteria cri) throws Exception{
	
	ModelAndView mav=new ModelAndView("/bbsFree/bbsFreelist.do");
	
	PageMaker pageMaker=new PageMaker();
	pageMaker.setCri(cri);
	pageMaker.setTotalCount(100);
	
	List<Map<String,Object>> list = boardServcie.selectBoardList(cri);
	mav.addObject("list", list);
	mav.addObject("pageMaker", pageMaker);
	
	return mav;
}  
	



}//class end