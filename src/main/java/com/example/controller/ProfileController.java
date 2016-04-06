package com.example.controller;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.validation.Valid;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.model.FileBucket;
import com.example.model.User;
import com.example.model.dao.DBUserDao;
import com.example.util.FileValidator;

@Controller
public class ProfileController {

	static int numForFilename = 1;
	
	@Autowired
    FileValidator fileValidator;
	
	@InitBinder("fileBucket")
    protected void initBinderFileBucket(WebDataBinder binder) {
        binder.setValidator(fileValidator);
    }
    
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
	
/*	@RequestMapping(value = "/savePicture", method = RequestMethod.POST)
	public @ResponseBody String savePicture(){  
		return "SavedPicture";
	}  
	*/
	
	 @RequestMapping(value = "/singleUpload", method = RequestMethod.GET)
	    public String getSingleUploadPage(ModelMap model) {
	        FileBucket fileModel = new FileBucket();
	        model.addAttribute("fileBucket", fileModel);
	        return "ChangePicture";
	    }
	 
	    @RequestMapping(value = "/savePicture", method = RequestMethod.POST)
	    public String savePicture(@Valid FileBucket fileBucket,
	            BindingResult result, ModelMap model, HttpServletRequest request) throws IOException {
	 
	    	if (result.hasErrors()) {
	            System.out.println("validation errors");
	            return "ChangePicture";
	        } else {
	            System.out.println("Fetching file");
	            MultipartFile multipartFile = fileBucket.getFile();
	 
	            // Now do something with file...
	            String UPLOAD_LOCATION = request.getSession().getServletContext().getRealPath("/static/uploads/");
	            System.out.println(UPLOAD_LOCATION );
	            
	            String file = (String) request.getSession().getAttribute("email");
	            FileCopyUtils.copy(fileBucket.getFile().getBytes(), new File(UPLOAD_LOCATION+File.separator +file+".png"));
	            DBUserDao.getInstance().updateProfilePicture(file, UPLOAD_LOCATION+File.separator +file+".png");
	            String fileName = multipartFile.getOriginalFilename();
	            model.addAttribute("fileName", fileName);
	            return "success";
	        }
	    }
	// ne vrashtam saobshtenie dali vs updates sa minali uspeshno, zashtoto sled
	// EditProfile
	// userat se preprashta na stranica Profile i tam shte se vizualizirat
	// promenenite i ne promenenite
	private void updateUserProfile(User signedUser, String newFirstName, String newLastName, String newNickname,
			String newDescription) {
		DBUserDao dao = DBUserDao.getInstance();
		if(dao.updateProfile(signedUser, newFirstName, newLastName, newNickname, newDescription)){
			signedUser.setFirstName(newFirstName);
			signedUser.setLastName(newLastName);
			signedUser.setNickname(newNickname);
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