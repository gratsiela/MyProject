package com.example.controller;

import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.Note;
import com.example.model.dao.DBNoteDao;

@Controller
public class PublicNotesController {

	@RequestMapping(value="/readPublicNote",method = RequestMethod.GET)
	public String readPublicNote(HttpServletRequest request,HttpSession session, Model model) {
		Long currentPublicNoteID=Long.parseLong(request.getParameter("currentPublicNoteID"));
		TreeMap<Long,Note>allPublicNotes=(TreeMap<Long, Note>) session.getAttribute("allPublicNotes");
		Note currentPublicNote=allPublicNotes.get(currentPublicNoteID);
		model.addAttribute("currentPublicNote", currentPublicNote);
		return "PublicNote";
	}
	
}
