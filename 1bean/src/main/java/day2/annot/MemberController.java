package day2.annot;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import day2.review.MemberService;
@Component	//bean id는 스프링컨테이너가 자동으로 memberController로 만든다.
public class MemberController {
//	@Autowired
//	@Qualifier("myMemberServiceImpl") //기본생성자 있을때만 가능
	private MemberService service;
	//사용할 수 있는 (의존관계 주입) 클래스를 MemberService의 구현체로한다.
	//해당클래스는 Your~Impl, My~Impl 클래스 2개이다.
	
	//생성자 의존관계 자동 주입 : 현재 MemberService 타입객체가 1개일때만.
	//@Autowired 생략됨. 생성자에서만 생략함.
	public MemberController(@Qualifier("myservice")MemberService service) {
		System.out.println("MemberController create");
		this.service=service;	//의존관계 주입 : 생성
	}
	
	public void find(int idx) {
		System.out.println("MemberController find");
		service.find(idx);
	}
}
