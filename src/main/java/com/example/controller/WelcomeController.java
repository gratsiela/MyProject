package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.model.User;
import com.example.model.dao.DBUserDao;

@Controller
public class WelcomeController {

	@RequestMapping(value="/index",method = RequestMethod.GET)
	public String wellcome() {
		return "Welcome";
	}	
	
	@RequestMapping(value="/goToSignUp",method = RequestMethod.GET)
	public String goToSignUp() {
		return "SignUp";
	}
	
	@RequestMapping(value="/goToSignIn",method = RequestMethod.GET)
	public String goToSignIn() {
		return "SignIn";
	}
	
	@RequestMapping(value="/signUp",method = RequestMethod.POST)
	public String signUp(HttpServletRequest request, Model model) {
		String firstName=request.getParameter("firstName");
		String lastName=request.getParameter("lastName");
		String nickname=request.getParameter("nickname");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		if(signUpUser(firstName, lastName, nickname, email, password)){
		return "MainPage";}
		return "SignUp";
	}
	
	@RequestMapping(value="/signIn",method = RequestMethod.POST)
	public String signIn() {
		return "SignIn";
	}
	
	public static boolean signUpUser(String firstName, String lastName,String nickname, String email, String password) {
		DBUserDao dao = DBUserDao.getInstance();
		List<User> users = null;

		users = dao.getAllUsers();

		if (users != null) {
			for (User u : users) {
				if (u.getEmail().equals(email)) {
					System.out.println("User with this email exists!");
					return false;
				}
			}
		}

		User newUser = new User(firstName, lastName, nickname ,email, password);
		dao.addUser(newUser);
		return true;
	}
}
