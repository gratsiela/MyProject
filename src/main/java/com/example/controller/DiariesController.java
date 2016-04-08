package com.example.controller;

import java.io.IOException;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.basic.BasicDesktopIconUI;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.Diary;
import com.example.model.Note;
import com.example.model.User;
import com.example.model.dao.DBDiaryDao;
import com.example.model.dao.DBNoteDao;

import scala.annotation.meta.setter;

@Controller
public class DiariesController {

	@RequestMapping(value = "/createNewDiary", method = RequestMethod.GET)
	public String goToCreateNewDiary() {
		return "CreateNewDiary";
	}

	@RequestMapping(value = "/createNewDiary", method = RequestMethod.POST)
	public String createNewDiary(HttpServletRequest request,HttpServletResponse response, HttpSession session) {
		User signedUser=(User) session.getAttribute("signedUser");
		String newDiaryName=request.getParameter("newDiaryName");
		if(!diaryExists(signedUser,newDiaryName)){
		DBDiaryDao dao=DBDiaryDao.getInstance();
		dao.addDiary(signedUser, newDiaryName);}
        return "redirect:/diaries";
	}
	
	@RequestMapping(value = "/diary", method = RequestMethod.GET)
	public String diaryGET(HttpServletRequest request,Model model, HttpSession session) {
		Long currentDiaryID=Long.parseLong(request.getParameter("currentDiaryID"));
		User signedUser=(User) session.getAttribute("signedUser");
		Diary currentDiary=signedUser.getDiaries().get(currentDiaryID);
		fillCurrentDiary(currentDiary,model,session);
		return "Diary";
	}
	
	@RequestMapping(value = "/diary", method = RequestMethod.POST)
	public String diaryPOST(HttpServletRequest request,Model model, HttpSession session) {
		Diary currentDiary=(Diary) session.getAttribute("currentDiary");
		fillCurrentDiary(currentDiary,model,session);
		return "Diary";
	}
	
	@RequestMapping(value = "/deleteDiary", method = RequestMethod.GET)
	public String goToDeleteDiary() {
		return "DeleteDiary";
	}
	
	@RequestMapping(value = "/deleteDiary", method = RequestMethod.POST)
	public String deleteDiary(HttpSession session) {
		Diary currentDiary=(Diary) session.getAttribute("currentDiary");
		DBDiaryDao dao=DBDiaryDao.getInstance();
		dao.deleteDiary(currentDiary.getId());
		return "redirect:diaries";
	}
	
	private boolean diaryExists(User user, String diaryName){
		TreeMap<Long,Diary> diaries=user.getDiaries();
		for(Entry<Long,Diary> entry:diaries.entrySet()){
		if(entry.getValue().getName().equals(diaryName)){
			return true;
		}}
		return false;
	}
	
	private void fillCurrentDiary(Diary currentDiary, Model model,HttpSession session){
		User signedUser=(User) session.getAttribute("signedUser");
		DBNoteDao dao=DBNoteDao.getInstance();
		TreeMap<Long,Note> currentDiaryNotes=dao.getAllNotes(signedUser,currentDiary);
		currentDiary.setNotes(currentDiaryNotes);
		model.addAttribute("currentDiary", currentDiary);
		session.setAttribute("currentDiary",currentDiary);
	}
}
