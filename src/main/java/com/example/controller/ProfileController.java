package com.example.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.User;
import com.example.model.dao.DBUserDao;

@Controller
public class ProfileController {

	@RequestMapping(value = "/editProfile", method = RequestMethod.GET)
	public String editProfile() {
		return "EditProfile";
	}

	@RequestMapping(value = "/saveProfile", method = RequestMethod.POST)
	public String saveProfile(HttpServletRequest request, HttpSession session) {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String nickname = request.getParameter("nickname");
		String description = request.getParameter("description");
		System.out.println(description);
		User signedUser = (User) session.getAttribute("signedUser");
		updateUserProfile(signedUser, firstName, lastName, nickname, description);
		return "Profile";
	}

	@RequestMapping(value = "/editPassword", method = RequestMethod.GET)
	public String editPassword() {
		return "EditPassword";
	}

	@RequestMapping(value = "/savePassword", method = RequestMethod.POST)
	public String savePassword(HttpServletRequest request, HttpSession session) {
		User signedUser = (User) session.getAttribute("signedUser");
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		if (!updatePassword(signedUser, oldPassword, newPassword))
			return "EditPassword";
		return "EditProfile";
	}
	
	@RequestMapping(value = "/changePicture", method = RequestMethod.GET)
	public String changePicture() {
		return "ChangePicture";
	}
	
	@RequestMapping(value = "/savePicture", method = RequestMethod.GET)
	public String savePicture() {
		// izvikvane na metod za zapazvane na snimkata
		return "EditProfile";
	}

	// ne vrashtam saobshtenie dali vs updates sa minali uspeshno, zashtoto sled
	// EditProfile
	// userat se preprashta na stranica Profile i tam shte se vizualizirat
	// promenenite i ne promenenite
	private void updateUserProfile(User signedUser, String newFirstName, String newLastName, String newNickname,
			String newDescription) {
		DBUserDao dao = DBUserDao.getInstance();
		if (!signedUser.getFirstName().equals(newFirstName)) {
			if (dao.updateFirstName(signedUser, newFirstName))
				signedUser.setFirstName(newFirstName);
		}
		if (!signedUser.getLastName().equals(newLastName)) {
			if (dao.updateLastName(signedUser, newLastName))
				signedUser.setLastName(newLastName);
		}
		if (!signedUser.getNickname().equals(newNickname)) {
			if (dao.updateNickname(signedUser, newNickname))
				signedUser.setNickname(newNickname);
		}
		if (!signedUser.getSelfDescription().equals(newDescription)) {
			System.out.println(newDescription);
			System.out.println(signedUser.getEmail());
			if (dao.updateSelfDescription(signedUser, newDescription))
				signedUser.setSelfDescription(newDescription);
		}
	}

	private boolean updatePassword(User signedUser, String oldPassword, String newPassword) {
		DBUserDao dao = DBUserDao.getInstance();
		if (!signedUser.getPassword().equals(oldPassword)) {
			return false;
		}
		if (!dao.updatePassword(signedUser, newPassword)) {
			signedUser.setPassword(newPassword);
			return false;
		}
		return true;
	}
}