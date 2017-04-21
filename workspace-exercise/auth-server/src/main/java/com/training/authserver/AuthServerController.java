package com.training.authserver;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.ValidationException;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.training.authserver.domain.Roles;
import com.training.authserver.domain.User;
import com.training.authserver.dto.UserDto;
import com.training.authserver.repository.UserRepository;
import com.training.authserver.service.AuthService;

@RestController
public class AuthServerController {
	
	@Inject
	AuthService authService;
	
	@Inject
	ModelMapper dtoMapper;
	
	@RequestMapping(path = "/admin/users", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<UserDto>> listUsers(){
		List<UserDto> userDtos = authService.listUsers().stream().map(u -> toDto(u)).collect(Collectors.toList());
		return new ResponseEntity<List<UserDto>>(userDtos, HttpStatus.OK);
	}
	
	@RequestMapping(path = "/admin/users", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<UserDto> addUser(@Valid @RequestBody UserDto uDto){
		String role = uDto.getRole();
		if (! Roles.ROLE_USER.getCanonicalName().equalsIgnoreCase(role) && ! Roles.ROLE_ADMIN.getCanonicalName().equalsIgnoreCase(role)){
			throw new ValidationException("Invalid role name: " + role);
		}
		User u = toDomain(uDto);
		authService.addUser(u);
		return new ResponseEntity<UserDto>(uDto, HttpStatus.CREATED);
	}
	
	@RequestMapping(path = "/admin/users/{userName}", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Object> deleteUser(@PathVariable("userName") String userName){
		authService.deleteUser(userName);
		return new ResponseEntity<Object>(null, HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public ResponseEntity<String> login(@RequestBody UserDto userDto){
		if (authService.isValidCredentials(userDto.getName(), userDto.getPassword())){
			return new ResponseEntity<String>("Auth Success", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Auth Failed", HttpStatus.UNAUTHORIZED);
		}
	}
	
	UserDto toDto(User u){
		UserDto uDto = new UserDto(u.getName(), u.getPassword(), u.getRole());
		return uDto;
	}
	
	User toDomain(UserDto uDto){
		User u = new User(uDto.getName(), uDto.getPassword(), uDto.getRole());
		return u;
	}
	
	@ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handle(ValidationException e) {
        return e.getMessage(); 
    }
}
