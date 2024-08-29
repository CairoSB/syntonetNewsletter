package com.br.service;

import org.apache.commons.validator.routines.UrlValidator;
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
		
		UrlValidator validator = new UrlValidator();
		
		//Checando se o link informado é uma URL valida ou não.
		if(validator.isValid(noticiaDTO.getLink())) {
			Noticia cliente = new Noticia(noticiaDTO.getTitulo(), noticiaDTO.getDescricao(), noticiaDTO.getLink());
			noticiasRepository.saveAndFlush(cliente);
		}
	}

}
