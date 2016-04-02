package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProfileController {
	
	@RequestMapping(value="/editProfile",method = RequestMethod.GET)
	public String editProfile() {
		return "EditProfile";
	}
	
	@RequestMapping(value="/saveProfile",method = RequestMethod.POST)
	public String saveProfile() {
		return "Profile";
	}
	
	@RequestMapping(value="/editPassword",method = RequestMethod.GET)
	public String editPassword() {
		return "EditPassword";
	}
	
	@RequestMapping(value="/savePassword",method = RequestMethod.POST)
	public String savePassword() {
		return "EditProfile";
	}
}
