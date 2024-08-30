package com.br.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = 2509935207689243100L;
	@NotBlank(message = "O nome e obrigatorio")
	@NotNull
	private String name;
	@NotBlank(message = "O email e obrigatorio")
	@NotNull
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