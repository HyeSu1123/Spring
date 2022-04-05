package day2.review;

public class MyMemberServiceImpl implements MemberService{
	
	private MemberDao dao;
	//사용할 수 있는 (의존관계 주입) 클래스를 MemberDao의 구현체로한다.
	//해당클래스는 Mybatis~Dao, Jdbc~Dao 클래스 2개이다.
		
	//MyMemberServiceImpl은 커스텀 생성자, 생성자 의존관계 주입으로 변경해서 해보기
	
	public MyMemberServiceImpl(MemberDao dao) {
		 System.out.println("MyMemberServiceImpl create"); 
		this.dao=dao;
	}//bean설정 파일의 bean생성내용 변경
	
	
//	 public MyMemberServiceImpl() {
//		 System.out.println("MyMemberServiceImpl create"); 
//	 }
	 
	
	public void setDao(MemberDao dao) { //의존관계주입 setter
		this.dao = dao;
	}

	@Override
	public void find(int idx) {
		//예) 검색 : 비지니스 로직 처리 : My방식으로
		System.out.println("MyLogic");
		dao.find(idx);
	}

}
