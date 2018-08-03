package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Test;

@Controller
public class WebController {

	@RequestMapping(value="/")
	private String layout(Model model) {
		
		Test test = new Test();
		test.setMe("abc");
		
		model.addAttribute("test", test);
		
		return "index";
	}
}
