package com.example.controller;

import java.util.TreeMap;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.Note;
import com.example.model.User;
import com.example.model.dao.DBNoteDao;
import com.example.model.dao.DBUserDao;

@Controller
public class AuthorProfileController {

	@RequestMapping(value="/authorProfile",method = RequestMethod.GET)
	public String authorProfile(HttpSession session, Model model) {
		User signedUser=(User) session.getAttribute("signedUser");
		Note currentPublicNote=(Note) session.getAttribute("currentPublicNote");
		User author=currentPublicNote.getAuthor();
		DBNoteDao dao=DBNoteDao.getInstance();
		TreeMap<Long,Note>followedUsersPublicNotes=dao.getFollowedUsersPublicNotes(signedUser);
		if(followedUsersPublicNotes.containsKey(currentPublicNote.getId())){
			model.addAttribute("followUnfollow", "unfollow");
		}
		else{
			model.addAttribute("followUnfollow", "follow");
		}
		session.setAttribute("author", author);
		model.addAttribute("author",author);
		return "AuthorProfile";
	}
	
	@RequestMapping(value="/follow",method = RequestMethod.POST)
	public String follow(HttpSession session) {
		User signedUser=(User) session.getAttribute("signedUser");
		User author=(User) session.getAttribute("author");
		DBUserDao dao=DBUserDao.getInstance();
		dao.follow(signedUser, author);
		return "redirect:authorProfile";
	}
	
	@RequestMapping(value="/unfollow",method = RequestMethod.POST)
	public String unfollow(HttpSession session) {
		User signedUser=(User) session.getAttribute("signedUser");
		User author=(User) session.getAttribute("author");
		DBUserDao dao=DBUserDao.getInstance();
		dao.unfollow(signedUser, author);
		return "redirect:authorProfile";
	}
}
