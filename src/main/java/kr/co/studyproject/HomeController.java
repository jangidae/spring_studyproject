package kr.co.studyproject;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
		// http:localhost:9100/home.do
		public HomeController() {
			System.out.println("----homeController() 객체 생성됨 ");
		}// end
		
		@RequestMapping("/home.do")
		public ModelAndView home() {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("home");
			
			
			mav.addObject("");
			return mav;
		}
		
		
		@RequestMapping("/template.do")
		public ModelAndView template() {
			ModelAndView tem = new ModelAndView();
			tem.setViewName("template");
			
			tem.addObject("");
			return tem;
		}
		
		
		
}
		

