package day1.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import day1.review.BuyController;

public class SpringApp {
	public static void main(String[] args) {
		
		//빈설정 파일을 읽어와서 빈(객체)를 생성한다. spring-context가 빈을 관리한다.
		ApplicationContext context = 
				new ClassPathXmlApplicationContext
				("classpath:META-INF/spring/applicationContext.xml");
		
		//위에서 만들어진 bean(객체) 중에서 id가 controller인 bean을 가져와 변수에 참조한다.
		//getBean 메소드 리턴타입이 Object : 참조타입에 맞게 캐스팅이 필요하다.
		BuyController controller = 
				(BuyController)context.getBean("controller");
		
		controller.buy();
		
	}
}
