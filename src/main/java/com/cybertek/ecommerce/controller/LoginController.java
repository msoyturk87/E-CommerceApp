package com.cybertek.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {


	// improve views maybe pictures
	@RequestMapping()
	public String login(){
		return "login";
	}
	
	@RequestMapping("/welcome")
	public String welcome(){
		return "welcome";
	}

}
