package com.example.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.Note;
import com.example.model.dao.DBNoteDao;

@Controller
public class PublicNotesController {

	@RequestMapping(value="/readPublicNote",method = RequestMethod.GET)
	public String readPublicNote(HttpServletRequest request,Model model) {
		Long currentPublicNoteID=Long.parseLong(request.getParameter("currentPublicNoteID"));
		DBNoteDao dao=DBNoteDao.getInstance();
		Note currentPublicNote=dao.getPublicNote(currentPublicNoteID);
		model.addAttribute("currentPublicNote", currentPublicNote);
		return "PublicNote";
	}
	
}
