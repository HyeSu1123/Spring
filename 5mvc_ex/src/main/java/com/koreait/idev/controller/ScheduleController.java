package com.koreait.idev.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.koreait.idev.mapper.ScheduleMapper;
import com.koreait.idev.model.Member;
import com.koreait.idev.model.Schedule;

@Controller
@RequestMapping("schedule/")
@SessionAttributes("member")
public class ScheduleController {
	private static final Logger logger = LoggerFactory.getLogger(ScheduleController.class);

	@Autowired
	ScheduleMapper mapper;		//dao 역할. 의존관계 주입 
	
	@GetMapping("/new.do")
	public String newdo(Model model,@SessionAttribute(value="member",required = false) Member member) {	//현재 로그인한 사용자의 스케쥴 리스트 가져와서 출력
		//로그인 상태가 아닐때 : 400오류 
		//->@SessionAttribute
		if(member!=null)
			model.addAttribute("list",mapper.getSchedules(member.getMno()));
		return "schedule/schedule"; //페이지 이동.
	}

	@GetMapping("/save.do") //form에서 받아왔던 데이터를 여기에 저장.
	public String save(Schedule sch, Model model) {
		logger.info("[My]" + sch);
		String sdate = sch.getSdate() + " " + sch.getStime();
		sch.setSdate(sdate);
		mapper.insert(sch);
		return "redirect:new.do";
	}
	
	@GetMapping("/delete.do")
	public void delete(int idx,@SessionAttribute(value ="member",required=false) Member member,
			HttpServletResponse response) throws IOException{
		String message;
		//로그인 상태가 아닐때는 member는 null
		if(member != null && mapper.checkMno(idx)== member.getMno()) { //mno 비교하기
				message="삭제되었습니다.";
				mapper.delete(idx);
		}
		else {
			message="삭제 오류입니다";
		}
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String url="./new.do";
		out.print("<script>alert('" +message +"');location.href='"+url+"'");
		out.print("</script>");
	}
}