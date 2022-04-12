package com.koreait.idev;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.koreait.idev.mapper.MemberMapper;
import com.koreait.idev.model.Member;

/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes("member")	//Model 저장소에 저장된 애트리뷰트 중에 member는 세션scope 값이라는 설정이다
public class indexController {
	
	private static final Logger logger = LoggerFactory.getLogger(indexController.class);
	
	@Autowired
	MemberMapper mapper;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(@ModelAttribute("success") String success, Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@GetMapping("/login.do")			//어노테이션 안쓰면 밑에 부분은 String success, Model model
	public String login(@ModelAttribute("success") String success) {
		
		//어노테이션이 없을 경우 model.addAttribute("success",success); //url 요청 파라미터가 남아있다.
		return "login";
	}
	
	@PostMapping("login.do")
	public String loginProc(@RequestParam Map<String,String> map, Model model) {
		logger.info("[my]"+map);
		Member member = mapper.login(map);	//로그인 성공하면 null 아닌 값 반환
		String url;
		if(member != null) {
			//성공 : main 화면, session 객체에 로그인 정보를 저장했다.(세션 애트리뷰트로 저장하기)
			model.addAttribute("member",member);	//@SessionAttributes로 설정하기.
			model.addAttribute("success","y");
			url = "/";
		}else {
			//실패 : 다시 로그인하러가기. alert메세지 띄우기 "로그인 정보가 올바르지 않습니다"
			url = "login.do?success=n";
			//rdattr.addFlashAttribute("success","n");	
			//요청 방식이 post일때만 url 표시되지 않도록 파라미터 값을 전달 할 수 있지만 여기서는 사용 못함.
		}
		return "redirect:" + url;
	}
	
	@GetMapping("/logout.do")
	public String logout(SessionStatus status) { //현재세션 상태 객체
		status.setComplete();	//@SessionAttributes로 설정된 애트리뷰트 값을 clear 한다.
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout2(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("member");
		session.invalidate();
		//서버가 JSESSIONID는 새로 부여해주지만 @SesstionAttributes로 설정된 값은 남아있다.
		return "redirect:/";
	}
	
	//@SessionAttributes로 설정된것은 SessionStatus 로 지운다.	
	//status.setComplete();   - JSESSIONID 는 변하지 않고 @SessionAttributes 로 설정된 애트리뷰트 값을 clear 한다.
//							  - HttpSession의 removeAttribute() 메소드 동작과 유사

	//jsp에서 로그아웃 : session.invalidate();	//JSESSIONID값을 새로운 값으로 한다.
	//				  session.removeAttribute("member");	// JSESSIONID 는 변하지 않고 값만 삭제
	
}
