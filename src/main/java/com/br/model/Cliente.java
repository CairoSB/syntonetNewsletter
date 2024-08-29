package com.br.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "cliente")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id",unique=true, nullable = false)
	private Long id;
	
	@Basic(optional = false)
	@Column(name = "name",unique=false, nullable = false)
	private String name;
	
	@Basic(optional = false)
	@Column(name = "email",unique=true, nullable = false)
	private String email;
	
	@Basic(optional = true)
	private String bDay;
	
	public Cliente(String name, String email, String bDay) {
		this.name = name;
		this.email = email;
		this.bDay = bDay;
	}
	
	@SuppressWarnings("unused")
	private Cliente() {
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
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
