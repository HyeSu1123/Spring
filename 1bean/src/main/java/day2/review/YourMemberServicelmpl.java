package day2.review;

public class YourMemberServicelmpl implements MemberService{
	
	private MemberDao dao;
	//사용할 수 있는 (의존관계 주입) 클래스를 MemberDao의 구현체로한다.
	//해당클래스는 Mybatis~Dao, Jdbc~Dao 클래스 2개이다.
	
	public void setDao(MemberDao dao) {
		this.dao = dao;
	}
	
	public YourMemberServicelmpl() {
		System.out.println("YourMemberServicelmpl create");
	}

	@Override
	public void find(int idx) {
		//예) 검색 비지니스 로직 처리 : Your 방식으로 
		System.out.println("Your Logic");
		dao.find(idx);
	}

}
