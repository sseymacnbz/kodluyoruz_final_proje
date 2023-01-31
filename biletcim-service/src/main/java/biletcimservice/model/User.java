package biletcimservice.model;

import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import biletcimservice.model.enums.UserType;
import biletcimservice.model.enums.Gender;
import biletcimservice.model.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name="user_")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int id;
	
	@Column(name="name", nullable=false)
	private String name;
	
	@Column(name="surname", nullable=false)
	private String surname;
	
	@Column(name="mail", nullable=false)
	private String mail;
	
	@Column(name="password", nullable=false)
	private String password;
	
	@Column(name="gender", nullable=false)
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	@Column(name="type", nullable=false)
	@Enumerated(EnumType.STRING)
	private UserType type;
	
	@Column(name="phone", length = 10, nullable=false)
	private String phone;
	
	@OneToMany(mappedBy="user")
	@JsonBackReference
	private List<Booking> bookings;
	
	@OneToMany(mappedBy="user")
	@JsonBackReference
	private List<Notification> notifications;
	
	@Column(name="role", nullable=false)
	@Enumerated(EnumType.STRING)
	private UserRole role; 
	
	public User(int id) {
		this.id = id;
	}
}
