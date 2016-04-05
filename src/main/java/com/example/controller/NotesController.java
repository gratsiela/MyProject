package com.example.controller;

import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.ssl.HttpSecureProtocol;
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
public class NotesController {

	@RequestMapping(value = "/createNewNote", method = RequestMethod.GET)
	public String goToCreateNewNote() {
		return "CreateNewNote";
	}

	@RequestMapping(value = "/createNewNote", method = RequestMethod.POST)
	public String createNewNote(HttpServletRequest request, HttpSession session) {
		Diary currentDiary=(Diary) session.getAttribute("currentDiary");
		String newNoteTitle=request.getParameter("title");
		String newNoteContent=request.getParameter("content");
		String newNoteStatus=request.getParameter("status");
		Note newNote=new Note(newNoteTitle, newNoteContent, newNoteStatus);
		if(!noteExists(currentDiary, newNote)){
			DBNoteDao dao=DBNoteDao.getInstance();
			dao.addNote(currentDiary, newNote);
		}
		return "Diary";
	}
	
	@RequestMapping(value = "/note", method = RequestMethod.GET)
	public String note(HttpServletRequest request, Model model, HttpSession session) {
		String currentNoteTitle=request.getParameter("currentNoteTitle");
		Diary currentDiary=(Diary) session.getAttribute("currentDiary");
		Note currentNote=currentDiary.getNotes().get(currentNoteTitle);
		model.addAttribute("currentNote",currentNote);
		session.setAttribute("currentNote",currentNote);
		return "Note";
	}
	
	@RequestMapping(value = "/deleteNote", method = RequestMethod.GET)
	public String goToDeleteNote() {
		return "DeleteNote";
	}
	
	@RequestMapping(value = "/deleteNote", method = RequestMethod.POST)
	public String goToDeleteDiary(HttpServletRequest request,HttpSession session) {
		Note currentNote=(Note) session.getAttribute("currentNote");
		DBNoteDao dao= DBNoteDao.getInstance();
		dao.deleteNote(currentNote);
		
		Diary currentDiary=(Diary) session.getAttribute("currentDiary");
		
		request.setAttribute("currentDiaryName", currentDiary.getName());
		return "forward:diary";
	}
	
	private boolean noteExists(Diary diary, Note note){
		TreeMap<String,Note> notes=diary.getNotes();
		if(notes.containsKey(note.getTitle())){
			return true;
		}
		return false;
	}
}
