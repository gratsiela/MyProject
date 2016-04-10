package com.example.controller;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	public String editProfile(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("signedUser") != null){
			return "EditProfile";
		}
		else{
			return "Welcome";
		}
	}

	@RequestMapping(value = "/saveProfile", method = RequestMethod.POST)
	public String saveProfile(HttpServletRequest request, HttpSession session) {
		if(session.getAttribute("signedUser") != null){
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String nickname = request.getParameter("nickname");
			String description = request.getParameter("description");
			User signedUser = (User) session.getAttribute("signedUser");
			updateUserProfile(signedUser, firstName, lastName, nickname, description);
			return "Profile";
		}
		else{
			return "Welcome";
		}
	}

	@RequestMapping(value = "/editPassword", method = RequestMethod.GET)
	public String editPassword(HttpSession session) {
		if(session.getAttribute("signedUser")!= null)
			return "EditPassword";
		return "Welcome";
	}

	@RequestMapping(value = "/savePassword", method = RequestMethod.POST)
	public String savePassword(HttpServletRequest request, HttpSession session, Model model) {
		if(session.getAttribute("signedUser") != null){
		User signedUser = (User) session.getAttribute("signedUser");
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		if(WelcomeController.passwordValidation(newPassword)){
			if (!updatePassword(signedUser, oldPassword, newPassword)){
				model.addAttribute("changePSWDmessage", "Wrong password!");
				return "EditPassword";}
		}
		else{
			model.addAttribute("changePSWDmessage", "The password must be between 5 and 30 symbols and must contains at least one upper case, one lower case and one digit!");
			return "EditPassword";}		
		}
		return "Welcome";
	}
	
	@RequestMapping(value = "/changePicture", method = RequestMethod.GET)
	public String changePicture(HttpSession session) {
		if(session.getAttribute("signedUser")!=null){
			return "ChangePicture";
		}
		return "Welcome";
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
	 
	/*    @RequestMapping(value = "/savePicture", method = RequestMethod.POST)
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
	    }*/
	 
/*	 @RequestMapping(value = "/savePicture", method = RequestMethod.POST)
	   public String savePicture(@RequestParam("file") MultipartFile file, HttpServletRequest req) {
		       //save file
	      if (!file.isEmpty()) {
				try {
					byte[] bytes = file.getBytes();

					// Creating the directory to store file
					String path = req.getSession().getServletContext().getRealPath("/resources/");
					String title = (String) req.getSession().getAttribute("email");
					File f = new File(path+File.separator+title+".jpg");
					BufferedOutputStream stream = new BufferedOutputStream(
							new FileOutputStream(f));
					stream.write(bytes);
					stream.close();
					 DBUserDao.getInstance().updateProfilePicture(title, f.getAbsolutePath());
					 System.out.println("File saved");
				} catch (Exception e) {
					
					System.out.println(e.getMessage());
				}
			} else {
				System.out.println("Emplty file");
			}
		

	      return "success";
	   }*/
	
	@RequestMapping(value = "/savePicture", method = RequestMethod.POST)
	   public String savePicture(Model model, HttpServletRequest request) throws IOException, ServletException {
		Part picture=request.getPart("file");
    	String description=request.getParameter("description");
    	String email = (String) request.getSession().getAttribute("email");
    	User user=(User) request.getSession().getAttribute("loggedUser");
    	if(picture==null){
    		model.addAttribute("errorMessage", "Upload fail");
    		return "ChangePicture";
    	}
    	else if(DBUserDao.getInstance().uploadPicture(picture, email)){
    		return "success";
    	}
    	else{
    		model.addAttribute("errorMessage", "Upload fail");
    		return "ChangePicture";
    	}
	}
	
	@RequestMapping(value = "/showPicture", method = RequestMethod.GET)
	public String getPicture(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String email = (String)request.getAttribute("email");
		String photo = DBUserDao.getProfilePicture(email);
		if(photo!=null)
			System.out.println("Picture load");
		User u = (User) request.getSession().getAttribute("loggedUser");
		u.setPhoto(photo);
	//	request.setAttribute("picture", photo);
		return "Profile";
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
		String hashOldPass = DigestUtils.shaHex(oldPassword);
		if (!signedUser.getPassword().equals(hashOldPass)) {
			return false;
		}
		if (!dao.updatePassword(signedUser, newPassword)) {
			signedUser.setPassword(newPassword);
			return false;
		}
		return true;
	}
}