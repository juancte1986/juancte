package ar.edu.uces.progweb2.agenda.dao;

import java.util.List;
import java.util.Set;

import ar.edu.uces.progweb2.agenda.dto.FormLoginDTO;
import ar.edu.uces.progweb2.agenda.model.Guest;
import ar.edu.uces.progweb2.agenda.model.User;

public interface UserDao extends GenericDao<User>{
	
	public User getUser(FormLoginDTO form);
	public List<User> getUsers(String filter);
	public List<User> getUsers(List<Long> args);
}
