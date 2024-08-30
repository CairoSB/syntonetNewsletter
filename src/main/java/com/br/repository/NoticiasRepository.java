package com.br.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.br.model.Noticia;

public interface NoticiasRepository 
		extends JpaRepository<Noticia, Long> {
	
	@Query("UPDATE noticia SET processada = true")
	void processAll();
}
