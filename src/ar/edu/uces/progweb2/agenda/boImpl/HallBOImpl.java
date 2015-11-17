package ar.edu.uces.progweb2.agenda.boImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.edu.uces.progweb2.agenda.bo.HallBO;
import ar.edu.uces.progweb2.agenda.dao.HallDao;
import ar.edu.uces.progweb2.agenda.model.Hall;

@Component("hallBO")
public class HallBOImpl implements HallBO{

	private HallDao hallDao;
	
	@Autowired
	public void setHallDao(HallDao hallDao) {
		this.hallDao = hallDao;
	}

	@Override
	public List<Hall> getHalls() {
		return this.hallDao.findAll();
	}

}
