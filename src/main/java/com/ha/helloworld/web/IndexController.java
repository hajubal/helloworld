package com.ha.helloworld.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ha.helloworld.service.EmployeeService;

@Controller
public class IndexController {

	@Resource(name="EmployeeService")
	private EmployeeService employeeSerice;
	
	@RequestMapping("/")
	String index() {
		return "index";
	}
	
	@RequestMapping("/home")
	String home() {
		return "home";
	}
	
	@RequestMapping("/main")
	String main() {
		return "main";
	}
}
