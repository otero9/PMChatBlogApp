package com.udc.muei.tfm.profiledataservice.model.user;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

/*
 * 
 * The Class UserDetailsImpl.
 * 
 * @author a.oteroc
 * 
 */
public class UserDetailsDTO implements UserDetails {

	private static final long serialVersionUID = 1L;

	private String id;

	private String username;

	private String email;

	private String firstname;

	private String lastname;

	private Date birthDate;

	private Date createdDate;

	private long points;

	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsDTO(String id, String username, String email, String password, String firstname, String lastname,
			Date birthDate, Date createdDate, long points) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthDate = birthDate;
		this.createdDate = createdDate;
		this.points = points;
	}

	public static UserDetailsDTO build(User user) {

		return new UserDetailsDTO(user.getUserId(), user.getUserName(), user.getEmail(), user.getPassword(),
				user.getFirstName(), user.getLastName(), user.getBirthdate(), user.getCreateDate(), user.getPoints());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public String getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public long getPoints() {
		return points;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsDTO user = (UserDetailsDTO) o;
		return Objects.equals(id, user.id);
	}
}
