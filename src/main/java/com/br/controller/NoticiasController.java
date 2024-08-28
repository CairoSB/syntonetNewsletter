package com.br.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.br.dto.NoticiaDTO;
import com.br.model.Noticia;
import com.br.repository.NoticiasRepository;
import com.br.service.CadastroNoticia;

@RestController
public class NoticiasController {
	
	@Autowired
	private NoticiasRepository noticiasRepository;
	
	@Autowired
	private CadastroNoticia noticiaService;
	
	@GetMapping(path = "api/noticias")
	public ResponseEntity<List<Noticia>> listNoticias() {
		return ResponseEntity
				.ok(noticiasRepository.findAll());
	}
	
	@PostMapping(path = "api/addNoticia")
	@ResponseStatus(value = HttpStatus.CREATED)
	public void addNoticia(@RequestBody NoticiaDTO noticiaDto) {
		noticiaService.add(noticiaDto);
	}

}
