package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	@RequestMapping(value="/myProfile",method = RequestMethod.GET)
	public String myProfile() {
		return "MyProfile";
	}
	
	@RequestMapping(value="/myDiaries",method = RequestMethod.GET)
	public String myDiaries() {
		return "MyDiaries";
	}
	
	@RequestMapping(value="/followed",method = RequestMethod.GET)
	public String followed() {
		return "Followed";
	}
	
	@RequestMapping(value="/all",method = RequestMethod.GET)
	public String wellcome() {
		return "All";
	}
}
