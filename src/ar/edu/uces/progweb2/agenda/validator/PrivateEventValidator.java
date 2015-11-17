package ar.edu.uces.progweb2.agenda.validator;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ar.edu.uces.progweb2.agenda.dto.FormEventDTO;
import ar.edu.uces.progweb2.agenda.utils.CalendarUtils;

@Controller
public class PrivateEventValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return FormEventDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "errors.event.name.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "date", "errors.event.date.empty");
		
		if (!errors.hasFieldErrors()) {
			
			FormEventDTO event = (FormEventDTO) obj;
			if(!CalendarUtils.isValidDate(event.getDate())){
				errors.rejectValue("date", "errors.event.date.format");
			}
			
			if(!errors.hasFieldErrors("date")){
				String start= event.getDate()+ " " + event.getStartTime(); 
				String end=  event.getDate()+ " " + event.getEndTime();
				Date startTime = CalendarUtils.getDateTime(start);
				Date endTime = CalendarUtils.getDateTime(end);
				if(!endTime.after(startTime)){
					errors.rejectValue("endTime", "errors.event.date.after");
				}
			}
				
		}
	}
}
