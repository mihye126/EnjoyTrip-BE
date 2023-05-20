package com.ssafy.trip;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//SWAGGER: API문서를 자동으로 생성해줌
@Configuration //xml을 대신한 설정 파일이다.
@EnableSwagger2 //swagger 기능 활성화
public class SwaggerConfiguration {
	//Docket문서가 만들어질 하나의 틀!
	
	
	@Bean
	public Docket makeDocket() {
		//문서 생성을 위한 가장 기본이 되는 객체
		Docket docket = new Docket(DocumentationType.SWAGGER_2); //문서 타입: DocumentationType.SWAGGER_2
		ApiSelectorBuilder builder = docket.select();
		
		//apis(): controller가 있는 패키지 지정
		builder = builder.apis(RequestHandlerSelectors.basePackage("com.ssafy.trip.interfaces.rest"));
		
		//paths(): api()로 선택된 api중 특정 path조건에 맞는 api들을 다시 필터링하여 문서화 함
				//없어도 api를 사용하는데 문제가 없다면 안넣어도 됨 => paths를 이용하여 필터링함
		builder = builder.paths(PathSelectors.ant("/**"));
					// /**: contextroot없어도 되고 있어도 되고, 그 뒤에 customers있어야 되고, /**: 그 뒤에 뭐 오든지 상관ㄴㄴ
		
		
		//문서 생성해서 docket에 넣음
		docket = builder.build(); // 문서 생성
		docket = docket.apiInfo(info());//추가적 정보 포함, 이건 없어도 됨
		
		
		return docket;
	}
	
	private ApiInfo info() {
		ApiInfoBuilder b = new ApiInfoBuilder();
		b = b.title("EnjoyTrip");
//		b = b.description("<h3>User</h3>Swagger를 이용한 User API<br><img src=image/f2.jpg width=150>");
		b = b.version("1.0");
		ApiInfo ai = b.build();
		
		return ai;
	}
}
