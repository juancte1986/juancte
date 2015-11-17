package ar.edu.uces.progweb2.agenda.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.uces.progweb2.agenda.bo.HallBO;
import ar.edu.uces.progweb2.agenda.model.Hall;
import ar.edu.uces.progweb2.agenda.service.HallService;

@Service("hallService")
public class HallServiceImpl implements HallService {

	private HallBO hallBO; 
	
	@Autowired
	public void setHallBO(HallBO hallBO) {
		this.hallBO = hallBO;
	}

	@Override
	public List<Hall> getHalls() {
		return this.hallBO.getHalls();
	}
	
}
