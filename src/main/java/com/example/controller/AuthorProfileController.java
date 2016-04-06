package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class AuthorProfileController {

	@RequestMapping(value="/authorProfile",method = RequestMethod.GET)
	public String authorProfile() {
		return "AuthorProfile";
	}
	
	@RequestMapping(value="/follow",method = RequestMethod.POST)
	public String follow() {
		return "AuthorProfile";
	}
}
