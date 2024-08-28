package com.br.dto;

import java.io.Serializable;

public class NoticiaDTO implements Serializable{

	private static final long serialVersionUID = 8304655103761416591L;
	private String titulo;
	private String descricao;
	private String link;
	
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
	
}