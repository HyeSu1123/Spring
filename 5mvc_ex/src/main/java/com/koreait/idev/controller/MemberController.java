package com.koreait.idev.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koreait.idev.mapper.MemberMapper;
import com.koreait.idev.model.Member;

@Controller
@RequestMapping(value = "member/")
public class MemberController {
	private static final Logger logger 
	= LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	MemberMapper mapper;		//dao 역할
	
	@GetMapping("/list.do")
	public String list(Model model) { //매개변수로 선언된 Model 객체를 이용하여
									  //지정된 view(또는 redirect url)로 데이터를 전달한다.
		List<Member> list = mapper.selectAll();
		model.addAttribute("list",list);	//view에서는 el ${list}로 데이터 가져오기
		return "member/MemberList";
	}
	
	@GetMapping("/join.do")
	public String join() {
		return "member/MemberForm";
	}
	
	@PostMapping("/join.do")
	public String insert(Member member) { //폼입력된 값의 name 속성과 Member객체가 매핑되어
											//데이터가 저장됩니다.
		//response.setCharacterEncoding("UTF-8");
		logger.info("[My]"+member);
		mapper.addMember(member);
		return "redirect:../";
	}  //회원가입 
	
	@GetMapping("/update.do")
	public String update() {
		return "member/MemberUpdate";
	}
	
	@PostMapping("/save.do")
	public String save() {
		return "redirect:member/update.do";
	}
	
	@GetMapping("/idCheck.do")
	public String idCheck(String email,Model model) { //검사하려고 했던 이메일은 요청 파라미터(매개변수 String email)로 불러온다.
		String msg;
		if(mapper.checkEmail(email) == 0)
			msg="사용할 수 있는 이메일 입니다.";
		else msg="사용할 수 없는 이메일 입니다.";
		model.addAttribute("email",email);
		model.addAttribute("msg",msg);
		return "member/idCheck"; //여기서 idCheck.jsp파일로 넘어감.
	} 
	
	
	
}