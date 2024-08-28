package com.br.scheduler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.br.model.Cliente;
import com.br.model.Noticia;
import com.br.repository.ClienteRepository;
import com.br.repository.NoticiasRepository;
import com.br.service.EmailService;

@Service
public class NoticiasScheduler {

	@Autowired
	private EmailService emailService;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private NoticiasRepository noticiasRepository;

	@Scheduled(cron = "0 0 8 * * *")
	private void enviarNoticias() {
		List<Noticia> noticias = noticiasRepository.findAll();
		List<Cliente> clientes = clienteRepository.findAll();
		for (Noticia noticia : noticias) {
			for (Cliente cliente: clientes) {
				emailService.sendSimpleMessage(cliente.getEmail(), noticia.getTitulo(), noticia.getDescricao());
			}
		}
	
	}

}
