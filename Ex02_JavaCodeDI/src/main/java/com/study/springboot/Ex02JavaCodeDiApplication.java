package com.study.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.study.springboot.bean.Config;
import com.study.springboot.bean.Member;
import com.study.springboot.bean.Printer;

@SpringBootApplication
public class Ex02JavaCodeDiApplication {

	public static void main(String[] args) {
//		SpringApplication.run(Ex02JavaCodeDiApplication.class, args);
		
		// 1. IoC 컨테이너 생성. @SpringBootApplication 역할을 함
		ApplicationContext context = 
				new AnnotationConfigApplicationContext(Config.class);
		
		// 2. Member Bean 가져오기
		// 주입을 먼저 받은 후 형변환 하기
		Member member1 = (Member)context.getBean("member1");
		// toString으로 반환
		member1.print();
		
		// 3. Member Bean 가져오기
		Member member2 = (Member)context.getBean("hello", Member.class);
		member2.print();
		
		// 4. PrinterB Bean 가져오기
		Printer printer = context.getBean("printerB", Printer.class);
		member1.setPrinter(printer);
		member1.print();
		
		// 5. 싱글톤인지 확인
		// 같은 클래스의 타입의 변수지만 서로 다른 메서드에서 각각 new를 통해 생성되었으므로,
		// 
		if (member1 == member2) {
			System.out.println("동일한 객체입니다.");
		}
		else {
			System.out.println("서로 다른 객체입니다.");
		}
	}

}
