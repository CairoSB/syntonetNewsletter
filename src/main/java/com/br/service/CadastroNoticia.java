package com.br.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.dto.NoticiaDTO;
import com.br.model.Noticia;
import com.br.repository.NoticiasRepository;

@Service
public class CadastroNoticia {
	@Autowired
	private NoticiasRepository noticiasRepository;
	
	public void add(NoticiaDTO noticiaDTO) {
		Noticia cliente = new Noticia(noticiaDTO.getTitulo(), noticiaDTO.getDescricao(), noticiaDTO.getLink());
		noticiasRepository.saveAndFlush(cliente);
	}

}
