package com.ha.hello;

import java.util.Calendar;
import java.util.Map.Entry;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

	//eclipse에서 spring boot application 실행 시 webapp 폴더가 deploy 되지 않는 문제가 있음.
	@RequestMapping("/time")
	String time(Model model) {
		model.addAttribute("time", Calendar.getInstance().getTime());

		StringBuilder env = new StringBuilder();
		
		for(Entry<String, String> item :  System.getenv().entrySet()) {
			env.append(String.format("Key: %s, Value: %s <br>", item.getKey(), item.getValue()));
		}
		
		model.addAttribute("env", env.toString());
		
		return "time";
	}
}
