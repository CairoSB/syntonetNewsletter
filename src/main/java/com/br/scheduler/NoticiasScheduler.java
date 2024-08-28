package com.br.scheduler;

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
			
			List<Noticia> noticiasParaEnviar = null;
			
			if(!noticia.getProcessada()) {
				noticiasParaEnviar.add(noticia);
				//To-do: Alterar valor do "Processada" desta noticia para true no banco.
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
				
				emailService.sendSimpleMessage(cliente.getEmail(), "Notícias do dia!", msg);
			}
		}
	
	}

}
