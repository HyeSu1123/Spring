package day3.spring;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringApp {

	public static void main(String[] args) {

		ApplicationContext context = 
				new ClassPathXmlApplicationContext("classpath:META-INF/spring/applicationContext.xml");
		//데이터베이스 연결이 되는지 확인
		SqlSessionTemplate sqlSession = 
				(SqlSessionTemplate) context.getBean("sqlSessionTemplate");
		System.out.println(sqlSession); //null 아닌값으로 출력이 있으면 연결 성공
	}

}
