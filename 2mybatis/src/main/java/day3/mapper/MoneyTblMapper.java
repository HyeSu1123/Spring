package day3.mapper;

import java.util.List;

import day3.dto.Money;

public interface MoneyTblMapper {
	
	void selectAll(Money momey);
	List<Money> selectAll();
}
