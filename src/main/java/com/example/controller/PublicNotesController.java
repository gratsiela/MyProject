package com.example.controller;

import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.neo4j.cypher.internal.compiler.v2_2.ast.rewriters.getDegreeOptimizer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.Note;
import com.example.model.User;
import com.example.model.dao.DBNoteDao;
import com.example.model.dao.DBUserDao;

@Controller
public class PublicNotesController {

	@RequestMapping(value="/readPublicNote",method = RequestMethod.GET)
	public String readPublicNote(HttpServletRequest request,HttpSession session, Model model) {
		Long currentPublicNoteID=Long.parseLong(request.getParameter("currentPublicNoteID"));
		TreeMap<Long,Note>notes;
		Note currentPublicNote;
		if(request.getParameter("authors").equals("allUsers")){
		notes=(TreeMap<Long, Note>) session.getAttribute("allPublicNotes");
		currentPublicNote=notes.get(currentPublicNoteID);}
		else{
			notes=(TreeMap<Long, Note>) session.getAttribute("followedUsersPublicNotes");
			currentPublicNote=notes.get(currentPublicNoteID);
		}
		session.setAttribute("currentPublicNote", currentPublicNote);
		model.addAttribute("currentPublicNote", currentPublicNote);
		return "PublicNote";
	}
	
	@RequestMapping(value="/authorProfile",method = RequestMethod.GET)
	public String authorProfile(HttpSession session, Model model) {
		Note currentPublicNote=(Note) session.getAttribute("currentPublicNote");
		User author=currentPublicNote.getAuthor();
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
}
