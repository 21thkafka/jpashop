package jpabook.jpashop;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JpashopApplication {
	public static void main(String[] args) {

		SpringApplication.run(JpashopApplication.class, args);

	}

	@Bean
	Hibernate5Module hibernate5Module(){
		Hibernate5Module hibernate5Module = new Hibernate5Module();
	//	hibernate5Module.configure(Hibernate5Module.Feature.FORCE_LAZY_LOADING, true);
		return hibernate5Module;			// 연관 데이터 모두 불러옴, 엔티티를 직접 노출시켜 안좋음, 성능 상 문제 생김
		//	return new Hibernate5Module();	// 지연 로딩을 무시하게 만듦, 연관 테이블 데이터 안불러옴
	}

}
