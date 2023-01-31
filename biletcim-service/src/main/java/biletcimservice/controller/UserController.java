package biletcimservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import biletcimservice.requests.UserAssignAdminRequest;
import biletcimservice.requests.UserCreateRequest;
import biletcimservice.service.UserService;
import biletcimservice.requests.UserLoginRequest;

@RestController
@RequestMapping(value="/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<String> create(@RequestBody UserCreateRequest createRequest){
		return ResponseEntity.ok(userService.create(createRequest));
	}
	
	@PostMapping(value="/login")
	public ResponseEntity<String> login(@RequestBody UserLoginRequest loginRequest) throws Exception{
		return ResponseEntity.ok(userService.login(loginRequest));
	}
	
	@PostMapping(value="/assignAdmin")
	public ResponseEntity<String> assignAdmin(@RequestBody UserAssignAdminRequest assignRequest){
		return ResponseEntity.ok(userService.assignAdmin(assignRequest));
	}
}
