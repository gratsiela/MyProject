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
		if(session.getAttribute("signedUser") != null){
			Long currentPublicNoteID;
			if(request.getParameter("currentPublicNoteID")!=null){
			currentPublicNoteID=Long.parseLong(request.getParameter("currentPublicNoteID"));
			TreeMap<Long,Note> notes=(TreeMap<Long, Note>) session.getAttribute("allPublicNotes");
			Note currentPublicNote=notes.get(currentPublicNoteID);
			session.setAttribute("currentPublicNote", currentPublicNote);
			model.addAttribute("currentPublicNote", currentPublicNote);
			session.setAttribute("subpage", "allPublicNotes");
			model.addAttribute("subpage", "allPublicNotes");}
			return "PublicNote";
		}
		else{
			return "SignIn";
		}
	}
	
	@RequestMapping(value="/readFollowedUserPublicNote",method = RequestMethod.GET)
	public String readFollowedUserPublicNote(HttpServletRequest request,HttpSession session, Model model) {
		if(session.getAttribute("signedUser") != null){
			Long currentPublicNoteID;
			if(request.getParameter("currentPublicNoteID")!=null){
			currentPublicNoteID=Long.parseLong(request.getParameter("currentPublicNoteID"));
			TreeMap<Long,Note> notes=(TreeMap<Long, Note>) session.getAttribute("followedUsersPublicNotes");
			Note currentPublicNote=notes.get(currentPublicNoteID);
			session.setAttribute("currentPublicNote", currentPublicNote);
			model.addAttribute("currentPublicNote", currentPublicNote);
			session.setAttribute("subpage", "followedUsersPublicNotes");
			model.addAttribute("subpage", "followedUsersPublicNotes");}
			return "PublicNote";
		}
		return "SignIn";
	}
}
