package biletcimservice.converter;

import org.springframework.stereotype.Component;


import biletcimservice.model.User;
import biletcimservice.model.enums.UserRole;
import biletcimservice.requests.UserCreateRequest;
import biletcimservice.util_.PasswordUtil;

@Component
public class UserConverter {
	
	public User convert(UserCreateRequest createRequest) {
		User user = new User();
		user.setGender(createRequest.getGender());
		user.setMail(createRequest.getMail());
		user.setName(createRequest.getName());
		user.setSurname(createRequest.getSurname());
		user.setPassword(PasswordUtil.preparePasswordHash(createRequest.getPassword(), createRequest.getMail()));
		user.setType(createRequest.getType());
		user.setPhone(createRequest.getPhone());
		user.setRole(UserRole.CUSTOMER);
		return user;
	}
}
