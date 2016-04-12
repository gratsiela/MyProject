package com.example.controller;

import java.sql.SQLException;
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
			return "Welcome";
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
		return "Welcome";
	}
	
	@RequestMapping(value="/readSearchedPublicNote",method = RequestMethod.GET)
	public String readSearchedPublicNote(HttpServletRequest request,HttpSession session, Model model) {
		if(session.getAttribute("signedUser") != null){
			Long currentPublicNoteID;
			if(request.getParameter("currentPublicNoteID")!=null){
			currentPublicNoteID=Long.parseLong(request.getParameter("currentPublicNoteID"));
			TreeMap<Long,Note> notes=(TreeMap<Long, Note>) session.getAttribute("searchedPublicNotes");
			Note currentPublicNote=notes.get(currentPublicNoteID);
			session.setAttribute("currentPublicNote", currentPublicNote);
			model.addAttribute("currentPublicNote", currentPublicNote);
			session.setAttribute("subpage", "searchedPublicNotes");
			model.addAttribute("subpage", "searchedPublicNotes");}
			return "PublicNote";
		}
		return "Welcome";
	}
	
	@RequestMapping(value="/searchedPublicNotes",method = RequestMethod.GET)
	public String goToSearchedPublicNotes(HttpServletRequest request,HttpSession session, Model model) {
		if(session.getAttribute("signedUser") != null){
			User signedUser=(User) session.getAttribute("signedUser");
			if(request.getParameter("searchedWords")!=null){
			String searchedWords=request.getParameter("searchedWords");
			try{
			DBNoteDao dao=DBNoteDao.getInstance();
			TreeMap<Long,Note> searchedPublicNotes=dao.search(signedUser,searchedWords);
			session.setAttribute("searchedPublicNotes",searchedPublicNotes);
			session.setAttribute("searchedWords",searchedWords);
			session.setAttribute("typeCurrentBack","searchedPublicNotes");
			model.addAttribute("searchedWords",searchedWords);
			model.addAttribute("searchedPublicNotes",searchedPublicNotes);
			}
			catch(ClassNotFoundException | SQLException e){
				return "ErrorPage";
			}
			}
			return "SearchedPublicNotes";
		}
		return "Welcome";
	}

}
