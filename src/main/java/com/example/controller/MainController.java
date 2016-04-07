package com.example.controller;

import java.util.TreeMap;
import java.util.TreeSet;

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
	public String myProfile() {
		return "Profile";
	}
	
	@RequestMapping(value="/diaries",method = RequestMethod.GET)
	public String myDiaries(HttpSession session,Model model) {
		User signedUser=(User) session.getAttribute("signedUser");
		DBDiaryDao dao=DBDiaryDao.getInstance();
		TreeMap<Long,Diary> signedUserDiaries=dao.getAllDiaries(signedUser);
		signedUser.setDiaries(signedUserDiaries);
		model.addAttribute("signedUser", signedUser);
		return "Diaries";
	}
	
	@RequestMapping(value="/followedUsersPublicNotes",method = RequestMethod.GET)
	public String goToFollowedUsersPublicNotes(HttpSession session,Model model) {
		User signedUser=(User) session.getAttribute("signedUser");
		DBNoteDao dao= DBNoteDao.getInstance();
		TreeMap<Long,Note> followedUsersPublicNotes=dao.getFollowedUsersPublicNotes(signedUser);
		session.setAttribute("followedUsersPublicNotes", followedUsersPublicNotes);
		model.addAttribute("followedUsersPublicNotes", followedUsersPublicNotes);
		return "FollowedUsersPublicNotes";
	}
	
	@RequestMapping(value = "/allPublicNotes", method = RequestMethod.GET)
	public String goToAllPublicNotes(Model model, HttpSession session) {
		User signedUser=(User) session.getAttribute("signedUser");
		DBNoteDao dao=DBNoteDao.getInstance();
		TreeMap<Long,Note>allPublicNotes=dao.getAllPublicNotes(signedUser);
		model.addAttribute("allPublicNotes",allPublicNotes);
		session.setAttribute("allPublicNotes",allPublicNotes);
		return "AllPublicNotes";
	}
	
	@RequestMapping(value="/signOut",method = RequestMethod.GET)
	public String signOut() {
		return "Welcome";
	}
}
