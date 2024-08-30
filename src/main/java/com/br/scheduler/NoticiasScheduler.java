package com.br.scheduler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.br.model.Cliente;
import com.br.model.Noticia;
import com.br.repository.ClienteRepository;
import com.br.repository.NoticiasRepository;
import com.br.service.EmailService;

import jakarta.mail.MessagingException;

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
		// Busca lista de todos clientes cadastrados no banco.
		List<Cliente> clientes = clienteRepository.findAll();
		// Busca lista de todas noticias cadastradas no banco.
		List<Noticia> noticias = noticiasRepository.findAll();
		// Ira guardar apenas as noticias que estiverem com "Processada" = "false"
		List<Noticia> noticiasParaEnviar = new ArrayList<>();
		
		// Altera o valor do "Processada" de todas noticias no banco para "true"
		noticiasRepository.processAll();
		
		// Filtra apenas as noticias não enviadas ainda (Noticias com "Processada" = "false") 
		for (Noticia noticia : noticias) {
			if(!noticia.getProcessada()) {
				noticiasParaEnviar.add(noticia);
			}
		}
		
		
		for (Cliente cliente: clientes) {
			String msg = new String();
			msg = "Bom dia " + cliente.getName() + " !";
			
			//To-do: Corrigir formato da data do bDay
			if (cliente.getbDay() == new Date().toString()) {
				msg += "\r\n Feliz aniversário!";
			}
			
			for (Noticia noticiaParaEnviar: noticiasParaEnviar) {
				//To-do: Verificar existencia de URL e tornar o titulo um link para a URL
				msg += "\r\n" + noticiaParaEnviar.getTitulo();
				msg += "\r\n" + noticiaParaEnviar.getDescricao();
			}
			
			try {
				emailService.sendSimpleMessage(cliente.getEmail(), "Notícias do dia!", msg);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
	
	}

}
