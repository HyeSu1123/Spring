package day3.mapper;

import java.util.List;

import day3.dto.Member;

public interface MemberTblMapper {
	//member.xml 매퍼파일과 연관되는 인터페이스
	//추상메소드는 매퍼파일의 id로 작성한다.
	
	void insert(Member member);
	List<Member> selectAll();
	
}
