package com.minse0.memo.helloworld;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Hellocontroller {
	
	@ResponseBody
	@GetMapping("/hello")
	public String helloWorld() {
		return "Hello World!!";
	}
	
	@GetMapping("hello/thymeleaf")
	public String hello() {
		return "hello/hello";
	}
}
