package com.example.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.model.Diary;
import com.example.model.PasswordSender;
import com.example.model.User;
import com.example.model.dao.DBUserDao;

@Controller
public class WelcomeController {

	@RequestMapping(value="/index",method = RequestMethod.GET)
	public String welcome() {
		return "Welcome";
	}	
	
	@RequestMapping(value="/signUp",method = RequestMethod.GET)
	public String goToSignUp() {
		return "SignUp";
	}
	
	@RequestMapping(value="/signIn",method = RequestMethod.GET)
	public String goToSignIn() {
		return "SignIn";
	}
	
	
	@RequestMapping(value="/signUp",method = RequestMethod.POST)
	public String signUp(HttpServletRequest request,Model model) {
		String firstName=request.getParameter("firstName");
		model.addAttribute("fname",firstName);
		String lastName=request.getParameter("lastName");
		model.addAttribute("lname",lastName);
		String nickname=request.getParameter("nickname");
		model.addAttribute("nickname",nickname);
		String email=request.getParameter("email");
		model.addAttribute("email",email);
		String password=request.getParameter("password");
		if(passwordValidation(password)){
			User user= signUpUser(model, firstName, lastName, nickname, email, password);
			if(user!=null){
				HttpSession session = request.getSession(true);
				session.setAttribute("signedUser", user);
				session.setAttribute("email", user.getEmail());
				model.addAttribute("signedUser", user);
				return "Profile";
			}
		}
		else{
			model.addAttribute("pswdMessage", "The password must be between 5 and 30 symbols and must contains at least one upper case, one lower case and one digit!");
			return "SignUp";
		}
		return "SignUp";
	}
	
	@RequestMapping(value="/signIn",method = RequestMethod.POST)
	public String signIn(HttpServletRequest request, Model model) {
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String hashPass = DigestUtils.shaHex(password);
		User user= signInUser(email, hashPass, model);
		if(user!=null){
			HttpSession session = request.getSession(true);
			session.setAttribute("signedUser", user);
			session.setAttribute("email", user.getEmail());//za da go vzema za ime na snimkata
			model.addAttribute("signedUser", user);
			return "Profile";
			}
		return "SignIn";
	}
	
	@RequestMapping(value="/forgottenPassword",method = RequestMethod.GET)
	public String forgottenPassword() {
		return "ForgottenPassword";
	}
	
	@RequestMapping(value="/sendForgottenPassword",method = RequestMethod.POST)
	public String sendForgottenPassword(HttpServletRequest request) {
		String email=request.getParameter("email");
		if(PasswordSender.sendPassword(email)){
			return "SignIn";
		}
		return "ForgottenPassword";
	}
	
	private User signUpUser(Model model,String firstName, String lastName,String nickname, String email, String password) {
		DBUserDao dao = DBUserDao.getInstance();
		List<User> users = null;
		try {
			users = dao.getAllUsers();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		User user=null;
		
		if (!users.isEmpty()) {
			for (User u : users) {
				if (u.getEmail().equals(email)) {
					model.addAttribute("emailMessage", "User with this email exists!");
					return user;
				}
			}
		}
		
		user = new User(firstName, lastName, nickname, email, password, "", "");
		dao.addUser(user);

		return user;
	}
	
	private User signInUser(String email, String password, Model model){
		User user=null;
		
		try {
			ArrayList<User> users = DBUserDao.getInstance().getAllUsers();
			if(users.isEmpty()){
				System.out.println("empty");
				model.addAttribute("errorMessage", "User does not exist, go to sign up!");
			}
			else{
				for(User u:users){
					if(u.getEmail().equals(email)&& u.getPassword().equals(password)){
						user=u;
					}
					else{
						model.addAttribute("errorMessage", "Wrong email or password!");
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
		public static boolean passwordValidation(String password){
		    String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{5,30}";
		    if(password.matches(pattern)){
		    	return true;
		    }
		    return false;
		  }
}
