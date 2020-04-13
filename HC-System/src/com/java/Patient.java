package com.java;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Transient;


@Entity
@Table(name = "patient")
public class Patient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Column(name="username",nullable=false,unique=true)
	@Email(message="please provide a valid e-mail")
	@NotEmpty(message="please provide an e-mail")
	private String email;
	
	@Column(name="password")
	@Transient
	private String password;
	
	@Column(name="first_name")
	@NotEmpty(message="please provide your first name")
	private String firstname;
	
	@Column(name="last_name")
	@NotEmpty(message="please provide your last name")
	private String lastname;
	
	@Column(name="enabled")
	private boolean enabled;
	
	@Column(name="confirmation_token")
	private String confirmationToken;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="authority")
	private String role;
	
	@Column(name="lastseen")
	@Transient
	private String lastseen;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getConfirmationToken() {
		return confirmationToken;
	}

	public void setConfirmationToken(String confirmationToken) {
		this.confirmationToken = confirmationToken;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getLastseen() {
		return lastseen;
	}

	public void setLastseen(String lastseen) {
		this.lastseen = lastseen;
	}
	
	
	@Override
	public String toString() {
		return "Patient[id= "+ id + ", email="+ email + ", password="+ password + ", firstname="+ firstname + ", lastname="+ lastname + ", enabled="+ enabled + ", confirmationToken="+ confirmationToken + ", gender="+ gender + ", role="+ role + ", lastseen="+ lastseen + "]";
	}
	
	
	
	
}
