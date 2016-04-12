package com.example.controller;

import java.sql.SQLException;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.Diary;
import com.example.model.Note;
import com.example.model.User;
import com.example.model.dao.DBDiaryDao;
import com.example.model.dao.DBNoteDao;
import com.example.model.dao.DBUserDao;
@Controller
public class MainController {

	@RequestMapping(value="/profile",method = RequestMethod.GET)
	public String myProfile(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user =(User)session.getAttribute("signedUser"); 
		String email = (String) session.getAttribute("email");
		if(user != null){
			String photo = DBUserDao.getProfilePicture(email);
			user.setPhoto(photo);
			request.setAttribute("user", user);
			return "Profile";
		}
		return "Welcome";
	}
	
	@RequestMapping(value="/diaries",method = RequestMethod.GET)
	public String myDiaries(HttpServletRequest request,Model model) {
		HttpSession session = request.getSession();
		if(session.getAttribute("signedUser") != null){
			System.out.println("session existed");
			User signedUser=(User) session.getAttribute("signedUser");
			DBDiaryDao dao;
			try {
				dao = DBDiaryDao.getInstance();
				TreeMap<Long,Diary> signedUserDiaries=dao.getAllDiaries(signedUser);
				signedUser.setDiaries(signedUserDiaries);
				model.addAttribute("signedUser", signedUser);
				return "Diaries";
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "ErrorPage";
			}
		}
		else{
			return "Welcome";
		}
	}
	
	@RequestMapping(value="/followedUsersPublicNotes",method = RequestMethod.GET)
	public String goToFollowedUsersPublicNotes(HttpServletRequest request,Model model) {
		HttpSession session = request.getSession();
		if(session.getAttribute("signedUser") != null){
			User signedUser=(User) session.getAttribute("signedUser");
			DBNoteDao dao;
			try {
				dao = DBNoteDao.getInstance();
				TreeMap<Long,Note> followedUsersPublicNotes=dao.getFollowedUsersPublicNotes(signedUser);
				session.setAttribute("followedUsersPublicNotes", followedUsersPublicNotes);
				model.addAttribute("followedUsersPublicNotes", followedUsersPublicNotes);
				return "FollowedUsersPublicNotes";
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "ErrorPage";
			}
			
		}
		else{
			return "Welcome";
		}
	}
	
	@RequestMapping(value = "/allPublicNotes", method = RequestMethod.GET)
	public String goToAllPublicNotes(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("signedUser") != null){
			User signedUser=(User) session.getAttribute("signedUser");
			DBNoteDao dao;
			try {
				dao = DBNoteDao.getInstance();
				TreeMap<Long,Note>allPublicNotes=dao.getAllPublicNotes(signedUser);
				model.addAttribute("allPublicNotes",allPublicNotes);
				session.setAttribute("allPublicNotes",allPublicNotes);
				session.setAttribute("typeCurrentBack", "allPublicNotes");

				return "AllPublicNotes";
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "ErrorPage";
			}
			
		}
		else{
			return "Welcome";
		}
	}
	
	@RequestMapping(value="/search",method = RequestMethod.GET)
	public String goToSearch(HttpSession session){
			if(session.getAttribute("signedUser")!=null){
			return 	"Search";
			}else{
				return "Welcome";
			}
	}

	
	@RequestMapping(value="/signOut",method = RequestMethod.GET)
	public String signOut(HttpServletRequest request, HttpSession session){
			if(session != null){
			    session.invalidate();
			    System.out.println("User logged out");
			}
			return "Welcome";
	}
		
}
