package com.ha.helloworld.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ha.helloworld.entity.Employee;

@Controller
public class IndexController {

	
	@RequestMapping("/")
	String index() {
		return "index";
	}
	
	@RequestMapping("userList")
	public String userList(Model model) {
		
		List<Employee> list = new ArrayList<Employee>();
		
		Employee emp = new Employee();
		emp.setId(1l);
		emp.setFirstName("user1");
		list.add(emp);
		
		emp = new Employee();
		emp.setId(2l);
		emp.setFirstName("user2");
		list.add(emp);
		
		model.addAttribute("userList", list);
		
		return "userList";
	}
}
