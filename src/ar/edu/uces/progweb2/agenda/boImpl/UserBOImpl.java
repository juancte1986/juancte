package ar.edu.uces.progweb2.agenda.boImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.edu.uces.progweb2.agenda.bo.UserBO;
import ar.edu.uces.progweb2.agenda.dao.UserDao;
import ar.edu.uces.progweb2.agenda.dto.FormLoginDTO;
import ar.edu.uces.progweb2.agenda.dto.UserAutocompleDTO;
import ar.edu.uces.progweb2.agenda.model.User;
import ar.edu.uces.progweb2.agenda.transformer.UserTransformer;

@Component("userBO")
public class UserBOImpl implements UserBO {
	
	private UserDao userDao;
	private UserTransformer transformer;
	
	@Autowired
	public void setTransformer(UserTransformer transformer) {
		this.transformer = transformer;
	}

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User getUser(FormLoginDTO form) {
		return this.userDao.getUser(form);
	}

	@Override
	public List<UserAutocompleDTO> getUsers(String filter) {
	   List<User> users =  this.userDao.getUsers(filter);
	   List<UserAutocompleDTO> usersDtos= new ArrayList<UserAutocompleDTO>();
	   for(User user : users){
		   usersDtos.add(this.transformer.transformToDTO(user));
	   }
	   return usersDtos;
	}

}
