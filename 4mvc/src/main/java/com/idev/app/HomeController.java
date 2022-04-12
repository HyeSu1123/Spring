package com.idev.app;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.idev.app.model.Order;

/**
 * 컨트롤러 클래스는 웹애플리케이션의 요청을 처리한다.
 * Handles requests for the application home page.
 */
@Controller		//@Component 대신에 역할을 표시하는 어노테이션
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * @RequestMapping : url요청을 처리할 메소드를 지정하는 어노테이션
	 * 				   : 브라우저에 출력할 view파일은 리턴값으로 설정.
	 *  			   : value 속성값은 url 요청주소
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		//log : 프로그램 실행 기록을 남기는 것.
		//System.out.println 대신에 아래와 같이 실행 기록을 남길 수 있다.
		logger.info("[My]Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	@RequestMapping(value="test")
	public void test() {
		
	}
	
	@RequestMapping(value="hello")
	public void hello() {
		
	}
	
	@RequestMapping(value="spring")
	public String spring() {
		return "home";		//view 파일이 home.jsp가 된다.
	}
	
	//결론 : url 요청이름과 view 파일명은 같도록 하는 것이 기본 설정이다.
	//	   (url 요청이름은 프로젝트에 맞게 직접 설계한다.)
	//		view 파일을 변경하고 싶으면 return "파일명"으로 한다.
	@RequestMapping(value="search")
	public void search(String name, int age) {
		//메소드의 매개변수는 url 요청 파라미터(쿼리스트링)를 전달받게 된다 
		//중요 :  파라미터 이름과 변수명은 일치하도록한다.
		//int값은 파라미터는 기본이 String이므로 null일때 , int변환 못하고 오류
		logger.info("[My]name : "+name);
		logger.info("[My]age : " + age);
	}
	
	//orderForm.jsp를 화면에 띄운다.
	@RequestMapping(value = "order",method=RequestMethod.GET)
	public String form() {
		return "orderform";		//view 파일이름(확장자 제외) 지정
	}
	
	//폼 양식에 입력된 값을 가져오기.
	@RequestMapping(value = "order",method=RequestMethod.POST)
	public String order(Order order) {
		//메소드의 매개변수는 파라미터 (폼양식 입력값)가 여러개일때 model 객체를 사용한다.
		logger.info("[My]" + order); //확인
		return "home";	//url은 order,화면은 home.jsp
	}
	
	@RequestMapping(value="map")	//method 속성은 기본값 RequestMethod.GET
	public String map(@RequestParam Map<String,Object> param) {
		//@RequestParam : 이것은 요청 파라미터이다.
		//클래스 정의없이 Map을 model 객체로 사용하는 경우 : 반드시 @RequestParam 설정
		logger.info("[My]" + param);
		return "redirect:/"; //url이 context ~~/app/ 로 새로운 요청으로 응답
	}
	//-------위에는 --> view -> controller 메소드 또는 요청파라미터 -> controller 메소드
	
	//-------아래는 --> controller -> view 또는 새로운 요청의 파라미터)로 데이터를 전달
	
	@RequestMapping(value="model")
	public void model(Model model) {
		model.addAttribute("idx",23);
		model.addAttribute("page",11);
		
	}//view는 자동으로 model.jsp -> 이 요청에 대한 request, response를 jsp로 전달
	
	@RequestMapping(value="redirect")
	public String redirect(Model model) {
		model.addAttribute("idx",99);
		return "redirect:/";	
		//return "redirect:/?idx=99";와 동일한 실행결과
		//결론 : model은 redirect에서는 get 요청 파라미터값
		//실행 했을 때 url 확인 해보기. : http://localhost:8085/app/?idx=99
	}
	@RequestMapping(value="modelAttr")
	public void modelAttr(@ModelAttribute(value="idx")int idx) {
		//@ModelAttribute(value="idx") idx변수값은 Model 객체처럼
		//view에 전달된다.
		
		//결론 : http://localhost:8085/app/modelAttr?idx=101
		//idx는 파라미터 이면서 modelAttr.jsp로 전달하는 model객체
		
	}
	
	@RequestMapping(value="orderAttr")
	public String orderAttr(@ModelAttribute(value="order") Order order) {
		//order
		return "modelAttr";
	}
	@RequestMapping(value="orderform2")
	public void orderForm2() {
		
	}
	
}
