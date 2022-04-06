package day1.review;

public class Main {
	public static void main(String[] args) {
		
		//controller 클래스 테스트
		BuyDao dao = new BuyDao("Mybatis");
		BuyService service = new BuyService(dao);	//의존관계 주입
		BuyController shop = new BuyController(service); //의존관계 주입
		
		//위와 같이 객체를 생성하고 의존관계를 설정하는 것을 객체들의 강한 결합.
		
		shop.buy();
		
		//애플리케이션 실행은 여러환경에서 실행하게 된다. 
		//그리고 데이터베이스 연결도 다른 서버 다른 계정들을 사용하면서 실행이 된다.
		//controller에서 사용하는 service 클래스를 변경해야한다거나
		//service에서 사용하는 dao클래스를 변경해야하는 상황들이 많이 생기는데
		//이럴때 어떻게 변경해야할까? 기본방법은 코드를 수정하는 방법인데 이 방법은 모두 찾아다녀야해서 비효율적이다.
		//spring 프레임웍은 의존관계 주입을 spring에서 관리하는 방법으로 한다.
		
	}
}
