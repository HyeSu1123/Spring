package day2.annot;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import day2.review.MemberDao;
import day2.review.MemberService;

@Component("myservice") //value 속성 생략. bean id myService로 지정
 class MyMemberServiceImpl implements MemberService{
//	@Autowired
//	@Qualifier("jdbcMemberDao") //기본생성자 있을때만 가능
	private MemberDao dao;
	//사용할 수 있는 (의존관계 주입) 클래스를 MemberDao의 구현체로한다.
	//해당클래스는 Mybatis~Dao, Jdbc~Dao 클래스 2개이다.
		
	//커스텀 생성
	public MyMemberServiceImpl(@Qualifier("jdbcMemberDao")MemberDao dao) {
		 System.out.println("MyMemberServiceImpl create"); 
		this.dao=dao;
	}//bean설정 파일의 bean생성내용 변경
	
	 	
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
