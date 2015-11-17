package ar.edu.uces.progweb2.agenda.daoImpl;

import org.springframework.stereotype.Repository;
import ar.edu.uces.progweb2.agenda.dao.HallDao;

import ar.edu.uces.progweb2.agenda.model.Hall;

@Repository("hallDao")
public class HallDaoImpl extends GenericDaoImpl<Hall> implements HallDao{

}
