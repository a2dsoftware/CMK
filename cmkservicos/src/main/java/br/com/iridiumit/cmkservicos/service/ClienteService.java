package br.com.iridiumit.cmkservicos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.iridiumit.cmkservicos.modelos.Cliente;
import br.com.iridiumit.cmkservicos.repository.Clientes;


@Service
public class ClienteService{
	
	@Autowired
	private Clientes clientes;
	
	public void excluir(Long codigo) {
		clientes.deleteById(codigo);
	}
	
	public Cliente localizar(Long id){
		return clientes.getOne(id);
	}
	
	public Cliente localizarLogin(String email){
		return clientes.findByEmail(email);
	}
	
	public void incluir(Cliente cliente){
               
        clientes.save(cliente);
    }
	
	public void salvar(Cliente cliente) {
		
		clientes.save(cliente);
		
	}
	
}
