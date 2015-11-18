package ar.edu.uces.progweb2.agenda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes("user")
@Controller
public class CalendarController {
	
	@RequestMapping(value = "/calendar")
	public String getCalendar(ModelMap model){
		return "/jsp/calendar.jsp";
	}
}
