package com.example.controller;

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
@Controller
public class MainController {

	@RequestMapping(value="/profile",method = RequestMethod.GET)
	public String myProfile(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("signedUser") != null){
			return "Profile";
		}
		return "SignIn";
	}
	
	@RequestMapping(value="/diaries",method = RequestMethod.GET)
	public String myDiaries(HttpServletRequest request,Model model) {
		HttpSession session = request.getSession();
		if(session.getAttribute("signedUser") != null){
			System.out.println("session existed");
			User signedUser=(User) session.getAttribute("signedUser");
			DBDiaryDao dao=DBDiaryDao.getInstance();
			TreeMap<Long,Diary> signedUserDiaries=dao.getAllDiaries(signedUser);
			signedUser.setDiaries(signedUserDiaries);
			model.addAttribute("signedUser", signedUser);
		
			return "Diaries";
		}
		else{
			return "SignIn";
		}
	}
	
	@RequestMapping(value="/followedUsersPublicNotes",method = RequestMethod.GET)
	public String goToFollowedUsersPublicNotes(HttpServletRequest request,Model model) {
		HttpSession session = request.getSession();
		if(session.getAttribute("signedUser") != null){
			User signedUser=(User) session.getAttribute("signedUser");
			DBNoteDao dao= DBNoteDao.getInstance();
			TreeMap<Long,Note> followedUsersPublicNotes=dao.getFollowedUsersPublicNotes(signedUser);
			session.setAttribute("followedUsersPublicNotes", followedUsersPublicNotes);
			model.addAttribute("followedUsersPublicNotes", followedUsersPublicNotes);
			return "FollowedUsersPublicNotes";
		}
		else{
			return "SignIn";
		}
	}
	
	@RequestMapping(value = "/allPublicNotes", method = RequestMethod.GET)
	public String goToAllPublicNotes(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("signedUser") != null){
			User signedUser=(User) session.getAttribute("signedUser");
			DBNoteDao dao=DBNoteDao.getInstance();
			TreeMap<Long,Note>allPublicNotes=dao.getAllPublicNotes(signedUser);
			model.addAttribute("allPublicNotes",allPublicNotes);
			session.setAttribute("allPublicNotes",allPublicNotes);
			return "AllPublicNotes";
		}
		else{
			return "SignIn";
		}
	}
	
	@RequestMapping(value="/signOut",method = RequestMethod.GET)
	public String signOut(HttpServletRequest request, HttpSession session){
		//	HttpSession session = request.getSession(false);
			if(session != null){
			    session.invalidate();
			    
			    System.out.println("User logged out");
			}
			return "Welcome";
	}
		
}
