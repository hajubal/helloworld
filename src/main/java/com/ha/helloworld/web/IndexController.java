package com.ha.helloworld.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ha.helloworld.entity.Employee;
import com.ha.helloworld.service.EmployeeService;

@Controller
public class IndexController {

	@Resource(name="EmployeeService")
	private EmployeeService employeeSerice;
	
	@RequestMapping("/")
	String index() {
		//return "index";
		return "redirect:userList";
	}
	
	@RequestMapping("userList")
	public String userList(Model model) {
		
		List<Employee> list = this.employeeSerice.getAllEmployees();
		
		model.addAttribute("userList", list);
		
		return "userList";
	}
}
