package com.udc.muei.tfm.profiledataservice.controller.user;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.udc.muei.tfm.profiledataservice.model.user.UserDetailsDTO;
import com.udc.muei.tfm.profiledataservice.model.user.UserService;
import com.udc.muei.tfm.profiledataservice.utils.JwtUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/*
 * 
 * The Class UserController.
 * 
 * @author a.oteroc
 * 
 */
@RestController
@CrossOrigin
@RequestMapping("/profileDataService/userAPI")
@Api(tags = "User Controller", description = "This API has a CRUD for Users")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@ApiOperation(value = "Login User by UserName and Password")
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody(required = true) LoginRequestDTO loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsDTO userDetails = (UserDetailsDTO) authentication.getPrincipal();

		return ResponseEntity.ok(new JwtResponseDTO(jwt, userDetails.getId(), userDetails.getUsername(),
				userDetails.getEmail(), userDetails.getFirstname(), userDetails.getLastname(),
				userDetails.getBirthDate(), userDetails.getCreatedDate(), userDetails.getPoints()));
	}

	@ApiOperation(value = "Logout User")
	@PostMapping("/logout")
	public ResponseEntity<HttpStatus> logout() {
		SecurityContextHolder.getContext().setAuthentication(null);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ApiOperation(value = "Logout User")
	@PostMapping("/changePassword")
	public ResponseEntity<HttpStatus> changePassword(@RequestParam(required = true) String id,
			@Valid @RequestBody(required = true) UserDTO user) {
		try {
			if (id != null && !id.isEmpty() && user.getPassword() != null && !user.getPassword().isEmpty()
					&& user.getNewPassword() != null && !user.getNewPassword().isEmpty()) {

			} else {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}

			SecurityContextHolder.getContext().setAuthentication(null);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Check if exists the same UserName")
	@PostMapping("/existsUserName")
	public ResponseEntity<?> existsUserName(@RequestBody(required = true) String userName) {

		return null;
	}

	@ApiOperation(value = "Check if exists the same Email")
	@PostMapping("/existsEmail")
	public ResponseEntity<?> existsEmail(@RequestBody(required = true) String email) {

		return null;
	}

	@ApiOperation(value = "Signup new User")
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody(required = true) SignupRequestDTO signupRequest) {
		try {
			if (signupRequest != null && signupRequest.getUserName() != null && !signupRequest.getUserName().isEmpty()
					&& signupRequest.getBirthdate() != null && signupRequest.getEmail() != null
					&& !signupRequest.getEmail().isEmpty() && signupRequest.getFirstName() != null
					&& !signupRequest.getFirstName().isEmpty() && signupRequest.getPassword() != null
					&& !signupRequest.getPassword().isEmpty()) {

				if (userService.existsUserName(signupRequest.getUserName())) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: Username is already taken!");
				}

				if (userService.existsEmail(signupRequest.getEmail())) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: Email is already in use!");
				}

				UserDTO userDTO = new UserDTO();
				userDTO.setUserName(signupRequest.getUserName());
				userDTO.setFirstName(signupRequest.getFirstName());
				userDTO.setLastName(signupRequest.getLastName());
				userDTO.setEmail(signupRequest.getEmail());
				userDTO.setBirthdate(signupRequest.getBirthdate());
				userDTO.setLocation(signupRequest.getLocation());
				userDTO.setWebSite(signupRequest.getWebSite());
				userDTO.setBiography(signupRequest.getBiography());
				userDTO.setPmiProfile(signupRequest.getPmiProfile());
				userDTO.setNotificationEmails(signupRequest.isNotificationEmails());
				userDTO.setMarketingEmails(signupRequest.isMarketingEmails());
				userDTO.setPassword(encoder.encode(signupRequest.getPassword()));

				UserDTO newUser = userService.save(userDTO);

				if (newUser != null && newUser.getUserId() != null && !newUser.getUserId().isEmpty()) {

					return new ResponseEntity<>(newUser, HttpStatus.CREATED);
				}
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Search all Users")
	@GetMapping("/users")
	public ResponseEntity<List<UserDTO>> getUsers(@RequestParam(required = false) String keywords) {
		try {
			List<UserDTO> users = userService.getAllUsers();
			if (users != null && !users.isEmpty()) {
				return new ResponseEntity<>(users, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Search best Users")
	@GetMapping("/bestUsers")
	public ResponseEntity<List<UserDTO>> getBestUsers(@RequestParam(required = false) Integer numberOfUsers) {
		try {
			List<UserDTO> bestUsers = new ArrayList<UserDTO>();
			if (numberOfUsers != null && numberOfUsers > 0) {
				bestUsers = userService.getBestUsers(numberOfUsers);
			} else {
				bestUsers = userService.getBestUsers(10);
			}
			return new ResponseEntity<>(bestUsers, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Search User by UserName")
	@GetMapping("/getUserByUserName")
	public ResponseEntity<UserDTO> getUserByUserName(@RequestParam(required = true) String userName) {
		try {
			if (userName != null && !userName.isEmpty()) {
				UserDTO user = userService.findByUserName(userName);

				if (user != null && user.getUserId() != null && !user.getUserId().isEmpty()) {
					return new ResponseEntity<>(user, HttpStatus.OK);
				} else {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Update User")
	@PutMapping("/user")
	public ResponseEntity<?> updateUser(@RequestBody(required = true) UserDTO user) {
		try {
			if (user == null || user.getUserId() == null || user.getUserId().isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: invalid userId!");
			}
			UserDTO updatedUser = userService.update(user);
			if (updatedUser != null && updatedUser.getUserId() != null && !updatedUser.getUserId().isEmpty()) {
				return new ResponseEntity<>(updatedUser, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Remove User by ID")
	@DeleteMapping("/user")
	public ResponseEntity<?> deleteUserById(@RequestParam(required = true) @PathVariable("id") String id) {
		try {
			if (id == null || id.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: invalid userId!");
			}
			userService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Search User by ID")
	@GetMapping("/getUserById")
	public ResponseEntity<?> getUserById(@RequestParam(required = true) @PathVariable("id") String id) {
		try {

			if (id != null && !id.isEmpty()) {
				UserDTO userDto = userService.findByUserId(id);
				if (userDto != null && userDto.getUserId() != null && !userDto.getUserId().isEmpty()) {
					return new ResponseEntity<>(userDto, HttpStatus.OK);
				} else {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/addPoints")
	public ResponseEntity<?> addPoints(@RequestParam(required = true) @PathVariable("id") String id,
			@RequestParam(required = true) @PathVariable("points") Long points) {
		try {
			if (id != null && !id.isEmpty() && points != null) {
				UserDTO userDTO = userService.addPoints(id, points);
				if (userDTO != null && userDTO.getUserId() != null && !userDTO.getUserId().isEmpty()) {
					return new ResponseEntity<>(userDTO, HttpStatus.OK);
				} else {
					return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
				}
			} else {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
