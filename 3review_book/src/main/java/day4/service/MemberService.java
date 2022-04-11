package day4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import day4.dto.MemberDto;
import day4.mapper.MemberMapper;

@Component
public class MemberService {
	
	private MemberMapper mapper;
	
	//의존관계 주입 : ByType(타입일치하는 것)
	@Autowired	//생성자에서는 생략가능
	public MemberService(MemberMapper mapper) {
		this.mapper = mapper;
	}
	
	public void insert(MemberDto dto) {
		mapper.insert(dto);
	}
}
