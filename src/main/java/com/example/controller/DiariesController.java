package com.example.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.basic.BasicDesktopIconUI;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.User;
import com.example.model.dao.DBDiaryDao;

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
		DBDiaryDao dao=DBDiaryDao.getInstance();
		dao.addDiary(signedUser, newDiaryName);
        return "redirect:/diaries";
	}
}
