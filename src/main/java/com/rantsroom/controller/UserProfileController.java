package com.rantsroom.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rantsroom.model.Rant;
import com.rantsroom.model.User;
import com.rantsroom.model.UserProfile;
import com.rantsroom.service.RantServiceImpl;
import com.rantsroom.service.UserProfileService;
import com.rantsroom.service.UserService;
import com.rantsroom.validator.UserValidator;

@Controller
public class UserProfileController {	
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserService userService;
    @Autowired
    private RantServiceImpl rantServiceImpl;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private UserProfileService userProfileService;
    
  //Save the uploaded file to this folder
    public static String UPLOADED_FOLDER = System.getProperty("user.dir") + "\\src\\main\\webapp\\uploads";
    
    @RequestMapping(value = "/users/profile", method = RequestMethod.GET)
    public String welcome(Model model, Principal principal) {
    		
    	User user = userService.findByUsername(principal.getName());
    	model.addAttribute("user", user);
    	List<Rant> rants = rantServiceImpl.findAllById(user.getId());
    	if(rants.isEmpty())
    		logger.info("No rants found");
    	model.addAttribute("rants", rants);
        
        return "users/profile";
    }
    
	
	@RequestMapping(value = "/users/editProfile", method = RequestMethod.GET)
    public String editProfile(Model model,Principal principal) {
		
		User user = userService.findByUsername(principal.getName());
		model.addAttribute("userForm",user );
		model.addAttribute("userProfileForm", new UserProfile());
		return "users/editProfile";
    }
	
	@RequestMapping(value = "/users/editProfile", method = RequestMethod.POST)
    public String editProfile(@ModelAttribute("userForm") User userForm,RedirectAttributes redirectAttributes,
    		BindingResult bindingResult, Model model, HttpServletRequest request, Principal principal) {		
		
		userValidator.validateUpdate(userForm, bindingResult);
		if (bindingResult.hasErrors()) {
            return "users/editProfile";
        }
		else {
			
			User user = userService.findByUsername(principal.getName());//userService.findById(Id).get();
			updateUser(user, userForm);			
			userService.save(user);
			redirectAttributes.addFlashAttribute("profileUpdated","Your profile is updated succesfully");
			//model.addAttribute("profileUpdated","Your profile is updated succesfully");
			model.addAttribute("user", user);
			List<Rant> rants = rantServiceImpl.findAllById(user.getId());
	    	if(rants.isEmpty())
	    		logger.info("No rants found");
			model.addAttribute("rants", rants);
			
			return "redirect:/users/profile";
		}		
	}
	

	@RequestMapping(value = "/uploadphoto", method = RequestMethod.POST)
    public String profilePhotoUpload(@RequestParam("profilePhoto") MultipartFile file,
                                   RedirectAttributes redirectAttributes, Model model, Principal principal, UserProfile userProfileForm) {
		
		User user = userService.findByUsername(principal.getName());
		if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("upload_status", "Please select a file to upload");
            model.addAttribute("userForm", user);
            return "redirect:users/editProfile";
        }
		if(!file.getContentType().equals("image/jpeg")) {
			redirectAttributes.addFlashAttribute("upload_status", "Invalid file selected. Please select a JPG/PNG File");
            model.addAttribute("userForm", user);
            return "redirect:users/editProfile";
		}
		try {
				logger.info("UPLOADED FILE TYPE: "+file.getContentType());
				
				// Get the file and save it somewhere
				String arr[] = file.getOriginalFilename().split("\\.");
				String fileName = principal.getName() +"_"+ LocalDate.now()+"."+arr[arr.length-1];
				
				byte[] bytes = file.getBytes();            
				Path path = Paths.get(UPLOADED_FOLDER+"\\"+fileName);            
				Files.write(path, bytes,StandardOpenOption.CREATE,StandardOpenOption.TRUNCATE_EXISTING );
				
				try {
					UserProfile userProfile = user.getUserProfile();
					//userProfile.getUser().setUserProfile(userProfileForm);
					userProfile.setFileName(fileName);
					userProfileService.save(userProfile);
				} catch (NullPointerException e) {
					logger.error("No user profile found");
					userProfileForm.setFileName(fileName);
					userProfileForm.setUser(user);
					userProfileService.save(userProfileForm);			
				} 
				
				redirectAttributes.addFlashAttribute("upload_status","Photo uploaded successfully");
				redirectAttributes.addFlashAttribute("filename",fileName);				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			model.addAttribute("userForm", userService.findByUsername(principal.getName()));
			return "redirect:users/editProfile";
		
    }

	@RequestMapping(value = "/users/profile/settings", method = RequestMethod.GET)
	public String profileSettings(Model model,Principal principal) {
		
		User user = userService.findByUsername(principal.getName());    	
		model.addAttribute("user", user);
		model.addAttribute("info", "This part is under construction. Please check back later.");    	
		
		return "users/profile";
	}
	
	private void updateUser(User user, User userForm) {
		
		user.setFirstname(userForm.getFirstname());
		user.setLastname(userForm.getLastname());
		user.setPassword(userForm.getPassword());
		user.setPasswordConfirm(userForm.getPassword());
	}
	
	
}


/*public String getPath() throws UnsupportedEncodingException {
public static void main(String[] args) throws UnsupportedEncodingException {
	
	LocalDate today = LocalDate.now();
	System.out.println(today);
}

			String path = this.getClass().getClassLoader().getResource("").getPath();

			String fullPath = URLDecoder.decode(path, "UTF-8");

			String pathArr[] = fullPath.split("/src/main/webapp/uploads");
			
			System.out.println(fullPath);

			System.out.println(pathArr[0]);

			fullPath = pathArr[0];

			String reponsePath = System.getProperty("user.dir") + "\\src\\main\\webapp\\uploads";
			System.out.println(reponsePath);

			// to read a file from webcontent

			reponsePath = new File(fullPath).getPath() + File.separatorChar + "newfile.txt";
			return reponsePath;

			}*/

/*String currentUser = null;
    	try {
			currentUser = principal.getName();
			logger.info("CURRENT LOGGED-IN USER: ",currentUser);
    	} catch (NullPointerException e) {
			logger.info("No user logged in");
		}
    	User user = userService.findByUsername(currentUser);
    	model.addAttribute("user", user);		
		
		
		if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(uploadFolderPath + file.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }*/