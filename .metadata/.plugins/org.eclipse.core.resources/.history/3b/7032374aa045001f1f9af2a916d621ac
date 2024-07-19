package com.study.springboot.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
	/*
	 *  Bean : Spring이 IoC 방식으로 관리하는 객체
	 *  BeanFactory :스프링의 IoC를 담당하는 핵심 컨테이너
	 *  application context : 빈 팩도리를 확장한 IoC 컨테이너
	 */
	
	// @Bean을 통해 자바빈 생성 - 이때 참조변수명은 별도의 설정이 없으므로 
	
	@Bean
	public Member member1() {
		Member member1 = new Member();
		member1.setName("홍길동");
		member1.setNickname("도사");
		member1.setPrinter(new PrinterA());
		
		return member1;
	}
	
	@Bean(name="hello")
	public Member member2() {
		// 생성자를 이용한 의존성 주입
		// 인수 생성자를 통해 초기화 한다
		return new Member("전우치", "도사", new PrinterA());
	}
	
	@Bean
	public PrinterA printerA() {
		return new PrinterA();
	}
	
	@Bean
	public PrinterB printerB() {
		return new PrinterB();
	}
	
}
