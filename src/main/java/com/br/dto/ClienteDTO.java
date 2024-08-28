package com.br.dto;

import java.io.Serializable;

public class ClienteDTO implements Serializable{

	private static final long serialVersionUID = 2509935207689243100L;
	private String name;
	private String email;
	private String bDay;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getbDay() {
		return bDay;
	}
	public void setbDay(String bDay) {
		this.bDay = bDay;
	}

}