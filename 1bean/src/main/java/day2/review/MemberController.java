package day2.review;

public class MemberController {
	
	private MemberService service;
	//사용할 수 있는 (의존관계 주입) 클래스를 MemberService의 구현체로한다.
	//해당클래스는 Your~Impl, My~Impl 클래스 2개이다.
	
	public MemberController(MemberService service) {
		System.out.println("MemberController create");
		this.service=service;	//의존관계 주입 : 생성
	}
	
	public void find(int idx) {
		System.out.println("MemberController find");
		service.find(idx);
	}
}
