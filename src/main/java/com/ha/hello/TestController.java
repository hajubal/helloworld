package com.ha.hello;

import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

	//eclipse에서 spring boot application 실행 시 webapp 폴더가 deploy 되지 않는 문제가 있음.
	@RequestMapping("/time")
	String time(Model model) {
		model.addAttribute("time", Calendar.getInstance().getTime());
		
		return "time";
	}
}
