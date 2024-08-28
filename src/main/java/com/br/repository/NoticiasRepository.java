package com.br.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.model.Noticia;

public interface NoticiasRepository 
		extends JpaRepository<Noticia, Long> {

}
