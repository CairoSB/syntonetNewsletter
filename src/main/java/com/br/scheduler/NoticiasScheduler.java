package com.br.scheduler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
		
		// Filtra apenas as noticias não enviadas ainda (Noticias com "Processada" = "false") 
		for (Noticia noticia : noticias) {
			if(!noticia.getProcessada()) {
				noticiasParaEnviar.add(noticia);
			}
		}
		
		//	Verifica se a noticias a serem enviadas.
		if(noticiasParaEnviar.isEmpty()) {
			//	Sem noticias novas para serem enviadas hoje.
		} else {
			// Tendo noticias para serem enviadas, alteramos o valor do "Processada" de todas noticias no banco para "true"
			noticiasRepository.processAll();
			
			for (Cliente cliente: clientes) {
				String msg = "<!doctype html>\n" +
						"<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\"\n" +
						"      xmlns:th=\"http://www.thymeleaf.org\">\n" +
						"<head>\n" +
						"    <meta charset=\"UTF-8\">\n" +
						"    <meta name=\"viewport\"\n" +
						"          content=\"width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0\">\n" +
						"    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n" +
						"    <title>Email</title>\n" +
						"</head>\n" +
						"<body>\n" +
						"<div>Bom dia <b>" + cliente.getName() + "</b></div> \n \n";
				
				// Validando aniversario.
				if (validarAniversario(cliente.getbDay())) {
					msg += "<div> <b> Feliz aniversário! </b></div> \n \n";
				}
				
				//	Adicionando as noticias
				msg += "<div id=\"listNoticias\" > \n";
				for (Noticia noticiaParaEnviar: noticiasParaEnviar) {
					msg += "<div> \n";
					
					//	Adicionando titulo da noticia.
					if(noticiaParaEnviar.getLink().length() > 0) {
						msg += "<a href=\"" + noticiaParaEnviar.getLink() + "\"> " +  noticiaParaEnviar.getTitulo() + " </a>"; 
					} else {
						msg += noticiaParaEnviar.getLink();
					}
					
					//	Adicionando descrição da noticia.
					msg += "\n " + noticiaParaEnviar.getDescricao() + "\n ";
					msg += "\n </div> \n";
				}
				msg += "\n </div>";
				
				try {
					emailService.sendSimpleMessage(cliente.getEmail(), "Notícias do dia!", msg);
				} catch (MessagingException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private boolean validarAniversario(String bDay) {
		try {
			SimpleDateFormat df = new SimpleDateFormat("dd/MM");
			Date bDayDate = df.parse(bDay);
			String nowString = df.format(new Date());
			Date now = df.parse(nowString);
			if (bDayDate.compareTo(now) == 0) {
				return true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}
}
