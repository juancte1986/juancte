package ar.edu.uces.progweb2.agenda.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ar.edu.uces.progweb2.agenda.model.Hall;
import ar.edu.uces.progweb2.agenda.service.HallService;

@Controller
public class HallController {
	
	private HallService hallService;
	
	@Autowired
	public void setHallService(HallService hallService) {
		this.hallService = hallService;
	}

	@RequestMapping( value =  "/getHalls" )
	public @ResponseBody Map<String, Object> getHalls(){
		Map<String, Object> map = new HashMap<>();
		List<Hall> halls = this.hallService.getHalls();
		map.put("halls", halls);
		return map;
	}
}
