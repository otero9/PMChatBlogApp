package com.udc.muei.tfm.profiledataservice.controller.user;

import javax.validation.constraints.NotBlank;

/*
 * 
 * The Class LoginRequestDTO.
 * 
 * @author a.oteroc
 * 
 */
public class LoginRequestDTO {
	@NotBlank
	private String username;

	@NotBlank
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
