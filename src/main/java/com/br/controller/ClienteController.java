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

import com.br.dto.ClienteDTO;
import com.br.model.Cliente;
import com.br.repository.ClienteRepository;
import com.br.service.CadastroCliente;

import jakarta.validation.Valid;

@RestController
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private CadastroCliente clienteService;
	
	@GetMapping(path = "api/clientes")
	public ResponseEntity<List<Cliente>> listClientes() {
		return ResponseEntity
				.ok(clienteRepository.findAll());
	}
	
	@PostMapping(path = "api/addCliente")
	@ResponseStatus(value = HttpStatus.CREATED)
	public void addCliente(@Valid @RequestBody ClienteDTO clienteDto) {
		clienteService.add(clienteDto);
	}
	
}
