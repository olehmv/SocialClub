package plittr.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import plittr.entity.Plitter;
import plittr.service.PlitterService;

@Controller
@RequestMapping("/")
public class IndexController {
	@Autowired
	PlitterService plitterService;
	
	@Autowired
	MessageSource messageSource;

	@Autowired
	PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;
	
	@Autowired
	AuthenticationTrustResolver authenticationTrustResolver;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getPage(){
		return "index";
	}

	/**
	 * This method will provide the medium to add a new user.
	 */
	@RequestMapping(value = { "/registration" }, method = RequestMethod.GET)
	public String newPlitter(ModelMap model) {
		Plitter plitter = new Plitter();
		model.addAttribute("plitter", plitter);
		model.addAttribute("edit", false);
		model.addAttribute("loggedinplitter", getPrincipal());
		return "registration";
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * saving user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/registration" }, method = RequestMethod.POST)
	public String saveUser(@Valid Plitter plitter, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "registration";
		}

		/*
		 * Preferred way to achieve uniqueness of field [username] should be implementing custom @Unique annotation 
		 * and applying it on field [username] of Model class [Plitter].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 * 
		 */
		if(!plitterService.isPlitterUsernameUnique(plitter.getId(), plitter.getUsername())){
			FieldError usernameError =new FieldError("plitter","username",messageSource.getMessage("non.unique.username", new String[]{plitter.getUsername()}, Locale.getDefault()));
		    result.addError(usernameError);
			return "registration";
		}
		
		plitterService.savePlitter(plitter);

		model.addAttribute("success", "Plitter " + plitter.getUsername() + " "+ plitter.getEmail() + " registered successfully");
		model.addAttribute("loggedinplitter", getPrincipal());
		return "registrationsuccess";
	}


	/**
	 * This method will provide the medium to update an existing user.
	 */
	@RequestMapping(value = { "/edit-user-{username}" }, method = RequestMethod.GET)
	public String editPlitter(@PathVariable String username, ModelMap model) {
		Plitter plitter = plitterService.findByUsername(username);
		model.addAttribute("plitter", plitter);
		model.addAttribute("edit", true);
		model.addAttribute("loggedinplitter", getPrincipal());
		return "registration";
	}
	
	/**
	 * This method will be called on form submission, handling POST request for
	 * updating user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-plitter-{username}" }, method = RequestMethod.POST)
	public String updateUser(@Valid Plitter plitter, BindingResult result,
			ModelMap model, @PathVariable String username) {

		if (result.hasErrors()) {
			return "registration";
		}

		plitterService.savePlitter(plitter);
		model.addAttribute("success", "Plitter " + plitter.getUsername()+ " "+ plitter.getEmail() + " updated successfully");
		model.addAttribute("loggedinplitter", getPrincipal());
		return "registrationsuccess";
	}

	
	/**
	 * This method will delete an Plitter by it's username value.
	 */
	@RequestMapping(value = { "/delete-plitter-{username}" }, method = RequestMethod.GET)
	public String deletePlitter(@PathVariable String username) {
		plitterService.deletePlitter(plitterService.findByUsername(username));
		return "redirect:/";
	}
	

	/**
	 * This method handles login GET requests.
	 * If users is already logged-in and tries to goto login page again, will be redirected to list page.
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		if (isCurrentAuthenticationAnonymous()) {
			return "login";
	    } else {
	    	return "redirect:/";  
	    }
	}

	/**
	 * This method handles logout requests.
	 * Toggle the handlers if you are RememberMe functionality is useless in your app.
	 */
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){    
			//new SecurityContextLogoutHandler().logout(request, response, auth);
			persistentTokenBasedRememberMeServices.logout(request, response, auth);
			SecurityContextHolder.getContext().setAuthentication(null);
		}
		return "redirect:/login?logout";
	}

	/**
	 * This method returns the principal[user-name] of logged-in user.
	 */
	private String getPrincipal(){
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails)principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}
	
	/**
	 * This method returns true if users is already authenticated [logged-in], else false.
	 */
	private boolean isCurrentAuthenticationAnonymous() {
	    final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    return authenticationTrustResolver.isAnonymous(authentication);
	}

	
}
