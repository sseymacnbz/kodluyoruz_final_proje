package biletcimservice.requests;

import biletcimservice.model.enums.Gender;
import biletcimservice.model.enums.UserRole;
import biletcimservice.model.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserCreateRequest {
	
	private String name;
	private String surname;
	private String mail;
	private String password;
	private Gender gender;
	private UserType type;
	private String phone;
	private UserRole role=UserRole.CUSTOMER;
}
