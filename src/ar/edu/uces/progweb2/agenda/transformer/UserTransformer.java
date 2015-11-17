package ar.edu.uces.progweb2.agenda.transformer;

import org.springframework.stereotype.Component;

import ar.edu.uces.progweb2.agenda.dto.UserAutocompleDTO;
import ar.edu.uces.progweb2.agenda.model.User;

@Component
public class UserTransformer {

	public UserAutocompleDTO transformToDTO(User user) {
		UserAutocompleDTO userDTO = new UserAutocompleDTO();
		userDTO.setValue(user.getId());
		userDTO.setLabel(user.getName() +" "+ user.getSurname()+" "+ "(" + user.getUser() + ")");
		return userDTO;
	}
}
