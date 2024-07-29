package com.study.springboot.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyController {
	@Autowired
	MemberService memberService;
	
	@RequestMapping("/")
	public String root() throws Exception {
		return "menu";
	}
	

	@RequestMapping("/selectByNameLike")
	public String selectByNameLike(@RequestParam("name") String search,
									@RequestParam("page") String page,
									Model model) {
		System.out.println("***" + search + "***");
		System.out.println("***" + page + "***");
		
		String name = search + "%";
		Sort sort = Sort.by(Sort.Order.desc("name"));
		int nPage = Integer.parseInt(page)-1;
		
		Pageable pageable = PageRequest.ofSize(10).withPage(nPage).withSort(sort);
			
		Page<Member> result = memberService.selectNameLike(name, pageable);
		List<Member> content = result.getContent();
		long totalElements = result.getTotalElements();
		int totalPages = result.getTotalPages();
		int size = result.getSize();
		int pageNumber = result.getNumber()+1;
		int numberOfElements = result.getNumberOfElements();
		
		model.addAttribute("members", content);
		model.addAttribute("totalElements", totalElements);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("size", size);
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("numberOfElements", numberOfElements);
		
		return "select_name_list";
	}
	
	
}
