package ar.edu.uces.progweb2.agenda.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.LocaleResolver;

import ar.edu.uces.progweb2.agenda.dto.FormLoginDTO;
import ar.edu.uces.progweb2.agenda.dto.UserAutocompleDTO;
import ar.edu.uces.progweb2.agenda.helper.CookieHelper;
import ar.edu.uces.progweb2.agenda.model.User;
import ar.edu.uces.progweb2.agenda.service.UserService;
import ar.edu.uces.progweb2.agenda.validator.LoginValidator;

@SessionAttributes("user")
@Controller
public class UserController {

	private LoginValidator loginValidator;
	private UserService userService;
	private CookieHelper cookieHelper;
	private LocaleResolver localeResolver;

	@Autowired
	public void setLoginValidator(LoginValidator loginValidator) {
		this.loginValidator = loginValidator;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setCookieHelper(CookieHelper cookieHelper) {
		this.cookieHelper = cookieHelper;
	}
	
	@Autowired
	public void setSessionLocaleResolver(LocaleResolver localeResolver) {
		this.localeResolver = localeResolver;
	}

	@RequestMapping(value = "/initFormLogin")
	public String initFormLogin(ModelMap model, @CookieValue(value = "cookieCalendar", required = false) String cookieCalendar) {
		if (cookieCalendar != null && !cookieCalendar.isEmpty()) {
			FormLoginDTO form = new FormLoginDTO();
			form.setPassword(this.cookieHelper.getPassword(cookieCalendar));
			form.setUser(this.cookieHelper.getPassword(cookieCalendar));
			User user = this.userService.getUser(form);
			if (user != null) {
				model.addAttribute("user", user);
				return "/jsp/calendar.jsp";
			} else {
				// falta implementar el error de cookie inexistente
				model.addAttribute("error", true);
				return "/jsp/login.jsp";
			}

		}
		model.addAttribute("formLogin", new FormLoginDTO());
		return "/jsp/login.jsp";
	}

	@RequestMapping(value = "/processFormLogin", method = RequestMethod.POST)
	public String processFormLogin(@ModelAttribute("formLogin") FormLoginDTO form, BindingResult result,
			ModelMap model, HttpServletResponse response, HttpServletRequest request) {
		this.loginValidator.validate(form, result);
		if (result.hasErrors()) {
			return "/jsp/login.jsp";
		}
		User user = this.userService.getUser(form);
		if (user == null) {
			//implementar error de credenciales invalidas
			model.addAttribute("error", true);
			return "/jsp/login.jsp";
		}
		if(form.getRemember()){
			this.cookieHelper.createCookie(user.getPassword(), user.getUser(), response);
		}
		//setLocale
		this.localeResolver.setLocale(request, response, new Locale(user.getLocale()));
		model.addAttribute("user", user);
		return "/jsp/calendar.jsp";
	}

	@RequestMapping(value = "/logout")
	public String logout(ModelMap model, SessionStatus sessionStatus, HttpServletResponse response) {
		this.cookieHelper.deleteCookie(response);
		sessionStatus.setComplete();
		model.addAttribute("user", null);
		model.addAttribute("formLogin", new FormLoginDTO());
		return "/jsp/login.jsp";
	}
	
	// asincronico user autocomplete
	@RequestMapping(value="/getUsers")
	public @ResponseBody Map<String, Object> getUsers(@RequestParam("term") String filter){
		Map<String, Object> map = new HashMap<String, Object>();
		List<UserAutocompleDTO> users =  this.userService.getUsers(filter);
		map.put("users", users);	
		return map;
	}

}
