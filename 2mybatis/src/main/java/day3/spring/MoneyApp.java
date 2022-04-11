package day3.spring;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import day3.dto.Money;
import day3.mapper.MoneyTblMapper;

public class MoneyApp {

	public static void main(String[] args) {
		ApplicationContext context =
				new ClassPathXmlApplicationContext("classpath:META-INF/spring/applicationContext.xml");
		
		SqlSessionTemplate sqlsession =
				(SqlSessionTemplate)context.getBean("sqlSessionTemplate");
		System.out.println(sqlsession);
		
		MoneyTblMapper mapper = 
				(MoneyTblMapper)context.getBean("moneyTblMapper");
		
		List<Money> list = mapper.selectAll();
		System.out.println(list);
	}

}
