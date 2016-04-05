package com.example.controller;

import java.util.TreeMap;
import java.util.TreeSet;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.Diary;
import com.example.model.User;
import com.example.model.dao.DBDiaryDao;

@Controller
public class MainController {

	@RequestMapping(value="/profile",method = RequestMethod.GET)
	public String myProfile() {
		return "Profile";
	}
	
	@RequestMapping(value="/diaries",method = RequestMethod.GET)
	public String myDiaries(HttpSession session,Model model) {
		User signedUser=(User) session.getAttribute("signedUser");
		DBDiaryDao dao=DBDiaryDao.getInstance();
		TreeMap<String,Diary> signedUserDiaries=dao.getAllDiaries(signedUser);
		signedUser.setDiaries(signedUserDiaries);
		model.addAttribute("signedUser", signedUser);
		return "Diaries";
	}
	
	@RequestMapping(value="/followed",method = RequestMethod.GET)
	public String followed() {
		return "Followed";
	}
	
	@RequestMapping(value="/all",method = RequestMethod.GET)
	public String all() {
		return "All";
	}
	
	@RequestMapping(value="/signOut",method = RequestMethod.GET)
	public String signOut() {
		return "Welcome";
	}
}
