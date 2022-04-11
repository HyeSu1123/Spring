package day4.service;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import day4.dto.BookRentDto;
import day4.mapper.BookRentMapper;

@Component
public class BookRentService {
	private BookRentMapper mapper;
	
	public BookRentService(BookRentMapper mapper) {
		this.mapper = mapper;
	}
	
	/*	트랜잭션 : 하나의 세트(집합,그룹)로 실행되는 sql 명령들 특히 insert,update,delete
	 * 	예시로 사나가 모모한테 계좌이체를 한다면 (사나 잔액 - , 모모 잔액 + 되는 sql명령실행)
	 * 	2개가 동시에 정상실행되어야 한다. 그렇지 않고 하나라도 오류가 생기면 모두 취소가 되어야한다.
	 * */
	//insert와 update를 트랜잭션으로 처리하기 위한 어노테이션
	@Transactional
	public int insert(BookRentDto dto) {
		System.out.println("insert 전 dto rent_no : "+dto.getRent_no());
		int result = mapper.insert(dto);	//정상적으로 1개행 추가했으면 1을 리턴
		System.out.println("insert 후 dto rent_no : "+dto.getRent_no());
		mapper.updateExpDate(dto.getRent_no());	//update에 오류가 있으면 insert는 rollback
		return result;
	}
}
