package com.study.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MyController {
	
	@RequestMapping("/")
	public @ResponseBody String root() throws Exception {
		return "Form 데이터 전달받아 사용하기";
	}
	
	@RequestMapping("/test1")//JSP처럼 request해서 Parameter를 받음 - 잘 안쓰는 방식
	public String test1(HttpServletRequest httpServletRequest,
						Model model) {
		String id = httpServletRequest.getParameter("id");
		String name = httpServletRequest.getParameter("name");
		
		model.addAttribute("id", id);
		model.addAttribute("name", name);
		
		return "test1";
	}
	@RequestMapping("/test2")
	public String test2(@RequestParam("id") String id, 
						@RequestParam("name") String name, 
						Model model) {
		model.addAttribute("id", id);
		model.addAttribute("name", name);

		return "test2";	
	}
	/*
	 * 커맨드 객체를 통해 파라미터를 한꺼번에 받아 저장하고, model객체에 저장까지
	 * 해준다. 파라미터를 저장한 DTO를 통해 출력해야 하므로 view에서는 getter를
	 * 사용한다.
	 */
	@RequestMapping("/test3")
	public String test3(Member member, Model model) {
		return "test3";
	}
	
	@GetMapping("/test4/{studentId}/{name}")
	public String getStudent(@PathVariable("studentId") String studentId,
							@PathVariable("name") String name,
							Model model) {
		model.addAttribute("id", studentId);
		model.addAttribute("name", name);

		return "test4";	
	}
	
}
