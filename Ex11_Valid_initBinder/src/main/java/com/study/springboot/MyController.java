package com.study.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {
	
	@RequestMapping("/")
	public @ResponseBody String root() throws Exception {
		return "validator(2)";
	}
	
	@RequestMapping("/insertForm")
	public String insert1() {
		
		return "createPage";
	}
	
	@RequestMapping("/create")
	public String insert2(@ModelAttribute("dto") @Validated ContentDto contentDto,
							BindingResult result) {
		String page = "createDonePage"; {
			System.out.println(contentDto);
			
//			ContentValidator validator = new ContentValidator();
//			validator.validate(contentDto, result);
			if (result.hasErrors()) {
				if (result.getFieldError("writer") != null) {
					System.out.println("1 : " + result.getFieldError("writer").getCode());
				}
				if (result.getFieldError("content") != null) {
					System.out.println("2 : " + result.getFieldError("content").getCode());
				}
				page = "createPage";
			}
			
			return page;
		}
	}
	/*
	 * 어노테이션을 지정하여 해당 메서드를 프로젝트가 시작할 때 먼저 실행시킨다.
	 * 그러면 WebDataBinder 타입 변수에 우리가 사용할 유효성 검증 클래스가 
	 * 프로젝트 시작할 때 등록된다.
	 */
	@InitBinder //ContentValidator 대체
	protected void InitBinder(WebDataBinder binder) {
		//개별적으로 새 생성자를 사용할 필요 없음
		binder.setValidator(new ContentValidator());
	}
}
