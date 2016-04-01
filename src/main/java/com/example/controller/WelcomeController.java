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
	public String signUp(HttpServletRequest request,Model model) {
		String firstName=request.getParameter("firstName");
		String lastName=request.getParameter("lastName");
		String nickname=request.getParameter("nickname");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		User user= signUpUser(firstName, lastName, nickname, email, password);
		if(user!=null){
		model.addAttribute("loggedUser", user);
		return "MainPage";}
		return "SignUp";
	}
	
	@RequestMapping(value="/signIn",method = RequestMethod.POST)
	public String signIn(HttpServletRequest request) {
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		User user= signInUser(email, password);
		if(user!=null){
			return "MainPage";}
		return "SignIn";
	}
	
	private User signUpUser(String firstName, String lastName,String nickname, String email, String password) {
		DBUserDao dao = DBUserDao.getInstance();
		List<User> users = null;
		users = dao.getAllUsers();

		User user=null;
		
		if (users != null) {
			for (User u : users) {
				if (u.getEmail().equals(email)) {
					System.out.println("User with this email exists!");
					return user;
				}
			}
		}

		user = new User(firstName, lastName, nickname ,email, password);
		dao.addUser(user);
		return user;
	}
	
	private User signInUser(String email, String password){
		User user=null;
		for(User u:DBUserDao.getInstance().getAllUsers()){
			if(u.getEmail().equals(email)&& u.getPassword().equals(password)){
				user=u;
			}
		}
		return user;
	}
}
