package com.br.service;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.dto.ClienteDTO;
import com.br.model.Cliente;
import com.br.repository.ClienteRepository;

@Service
public class CadastroCliente {
	@Autowired
	private ClienteRepository clienteRepository;
	
	public void add(ClienteDTO clienteDto) {
		
		// Checando se o e-mail informado é valido ou não.
		if(EmailValidator.getInstance().isValid(clienteDto.getEmail())) {
			Cliente cliente = new Cliente(clienteDto.getName(), clienteDto.getEmail(), clienteDto.getbDay());
			clienteRepository.saveAndFlush(cliente);
		}
	}
}
