package com.br.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "noticia")
public class Noticia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id",unique=true, nullable = false)
	private Long id;
	
	@Basic(optional = false)
	@Column(name = "titulo",unique=false, nullable = false)
	private String titulo;
	
	@Basic(optional = false)
	@Column(name = "descricao",unique=false, nullable = false)
	private String descricao;
	
	@Basic(optional = true)
	private String link;
	
	private Boolean processada = false;
	
	public Noticia(String titulo, String descricao, String link) {
		this.titulo = titulo;
		this.descricao = descricao;
		this.link = link;
	}
	
	@SuppressWarnings("unused")
	private Noticia() {
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}

	public Boolean getProcessada() {
		return processada;
	}

	public void setProcessada(Boolean processada) {
		this.processada = processada;
	}

}
