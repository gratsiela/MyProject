package com.example.controller;

import java.sql.SQLException;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
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
	public String authorProfile(HttpSession session, Model model, HttpServletRequest request) {
		if(session.getAttribute("signedUser")!= null){
			User signedUser=(User) session.getAttribute("signedUser");
			Note currentPublicNote=(Note) session.getAttribute("currentPublicNote");
			User author=currentPublicNote.getAuthor();
			DBNoteDao dao;
			try {
				dao = DBNoteDao.getInstance();
			
				TreeMap<Long,Note>followedUsersPublicNotes=dao.getFollowedUsersPublicNotes(signedUser);
				if(followedUsersPublicNotes.containsKey(currentPublicNote.getId())){
					model.addAttribute("followUnfollow", "UNFOLLOW");
				}
				else{
					model.addAttribute("followUnfollow", "FOLLOW");
				}
				if(session.getAttribute("subpage").equals("followedUsersPublicNotes")){
					model.addAttribute("typeCurrentNote", "readFollowedUserPublicNote");
				}else{
					model.addAttribute("typeCurrentNote", "readPublicNote");
				}
				session.setAttribute("author", author);
				model.addAttribute("author",author);
				String photo = DBUserDao.getProfilePicture(author.getEmail());
				if(photo!=null){
					System.out.println("Picture load");
					author.setPhoto(photo);
					request.setAttribute("author", author);
				}
				return "AuthorProfile";
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "ErrorPage";
			}
		}
		else{
			return "Welcome";
		}
	}
	
	@RequestMapping(value="/authorProfile",method = RequestMethod.POST)
	public String authorProfilePOST(HttpServletRequest request,HttpSession session, Model model) {
		if(session.getAttribute("signedUser")!= null){
			User signedUser=(User) session.getAttribute("signedUser");
			User author;
			DBUserDao dao=null;
		
			try{
			if(request.getParameter("currentPublicAuthorEmail")!=null){
			String authorEmail=request.getParameter("currentPublicAuthorEmail");
			dao=DBUserDao.getInstance();
			author=dao.getUser(authorEmail);
			}else{
			dao=DBUserDao.getInstance();
			author=(User) session.getAttribute("author");
			}
			
			boolean follow=dao.checkFollowing(signedUser,author);
			
			if(follow){
				model.addAttribute("followUnfollow", "UNFOLLOW");
			}
			else{
				model.addAttribute("followUnfollow", "FOLLOW");
			}
			
			session.setAttribute("author", author);
			model.addAttribute("author",author);
			model.addAttribute("typeCurrentBack",session.getAttribute("typeCurrentBack"));
			String photo = DBUserDao.getProfilePicture(author.getEmail());
			if(photo!=null){
				System.out.println("Picture load");
				author.setPhoto(photo);
				request.setAttribute("author", author);
			}
			return "AuthorProfile";
			}catch(ClassNotFoundException | SQLException e){
				e.printStackTrace();
				return "ErrorPage";
			}
		}
		else{
			return "Welcome";
		}
}

	@RequestMapping(value="/FOLLOW",method = RequestMethod.POST)
	public String follow(HttpSession session) {
		if(session.getAttribute("signedUser") != null){
			User signedUser=(User) session.getAttribute("signedUser");
			User author=(User) session.getAttribute("author");
			DBUserDao dao;
			try {
				dao = DBUserDao.getInstance();
				dao.follow(signedUser, author);
				if(session.getAttribute("typeCurrentBack").equals("readPublicNote") || session.getAttribute("typeCurrentBack").equals("readFollowedUserPublicNote"))
				{return "redirect:authorProfile";}
				else{
					return "forward:authorProfile";
				}
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				return "ErrorPage";
			}
		}
		else{
			return "Welcome";
		}
	}
	
	@RequestMapping(value="/UNFOLLOW",method = RequestMethod.POST)
	public String unfollow(HttpSession session) {
		if(session.getAttribute("signedUser") != null){
			User signedUser=(User) session.getAttribute("signedUser");
			User author=(User) session.getAttribute("author");
			DBUserDao dao;
			try {
				dao = DBUserDao.getInstance();
				dao.unfollow(signedUser, author);
				
				if(session.getAttribute("typeCurrentBack").equals("readPublicNote") || session.getAttribute("typeCurrentBack").equals("readFollowedUserPublicNote")){
					return "redirect:authorProfile";
					}else{
					return "forward:authorProfile";}
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "ErrorPage";
			}
		
		}
		else{
			return "Welcome";
		}
	}


/*	
	@RequestMapping(value="/FOLLOW",method = RequestMethod.POST)
	public String follow(HttpSession session) {
		if(session.getAttribute("signedUser") != null){
			User signedUser=(User) session.getAttribute("signedUser");
			User author=(User) session.getAttribute("author");
			DBUserDao dao;
			try {
				dao = DBUserDao.getInstance();
				dao.follow(signedUser, author);
				return "redirect:authorProfile";
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "ErrorPage";
			}
			
		}
		else{
			return "Welcome";
		}
	}
	
	@RequestMapping(value="/UNFOLLOW",method = RequestMethod.POST)
	public String unfollow(HttpSession session) {
		if(session.getAttribute("signedUser") != null){
			User signedUser=(User) session.getAttribute("signedUser");
			User author=(User) session.getAttribute("author");
			DBUserDao dao;
			try {
				dao = DBUserDao.getInstance();
				dao.unfollow(signedUser, author);
				return "redirect:authorProfile";
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "ErrorPage";
			}
		
		}
		else{
			return "Welcome";
		}
	}*/
}
