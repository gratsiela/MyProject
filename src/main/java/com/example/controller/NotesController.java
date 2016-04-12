package com.example.controller;

import java.sql.SQLException;
import java.util.Date;
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
		if(session.getAttribute("signedUser") != null){
			User signedUser=(User) session.getAttribute("signedUser");
			Diary currentDiary=(Diary) session.getAttribute("currentDiary");
			String newNoteTitle=request.getParameter("title");
			String newNoteContent=request.getParameter("content");
			String newNoteStatus=request.getParameter("status");
			Note newNote=new Note(newNoteTitle, newNoteContent, newNoteStatus, new Date(),0L, signedUser);
			if(!noteExists(currentDiary, newNote)){
				DBNoteDao dao;
				try {
					dao = DBNoteDao.getInstance();
					dao.addNote(currentDiary, newNote);
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return "ErrorPage";
				}
				
			}
			return "forward:diary";
		}
		else{
			return "Welcome";
		}
	}
	
	@RequestMapping(value = "/note", method = RequestMethod.GET)
	public String note(HttpServletRequest request, Model model, HttpSession session) {
		if(session.getAttribute("signedUser") != null){
			Long currentNoteID=Long.parseLong(request.getParameter("currentNoteID"));
			Diary currentDiary=(Diary) session.getAttribute("currentDiary");
			Note currentNote=currentDiary.getNotes().get(currentNoteID);
			model.addAttribute("currentNote",currentNote);
			session.setAttribute("currentNote",currentNote);
			return "Note";
		}
		else{
			return "Welcome";
		}
	}
	
	@RequestMapping(value = "/deleteNote", method = RequestMethod.GET)
	public String goToDeleteNote(HttpSession session) {
		if(session.getAttribute("signedUser") != null){
			return "DeleteNote";
		}
		else{
			return "Welcome";
		}
	}
	
	@RequestMapping(value = "/deleteNote", method = RequestMethod.POST)
	public String goToDeleteDiary(HttpServletRequest request,HttpSession session) {
		if(session.getAttribute("signedUser") != null){
			Note currentNote=(Note) session.getAttribute("currentNote");
			DBNoteDao dao;
			try {
				dao = DBNoteDao.getInstance();
				dao.deleteNote(currentNote);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "ErrorPage";
			}
			
			return "forward:diary";
		}
		else{
			return "Welcome";
		}
	}
	
	private boolean noteExists(Diary diary, Note note){
		TreeMap<Long,Note> notes=diary.getNotes();
		if(notes.containsKey(note.getId())){
			return true;
		}
		return false;
	}
}
