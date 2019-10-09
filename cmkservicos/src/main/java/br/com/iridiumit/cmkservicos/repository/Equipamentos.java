package br.com.iridiumit.cmkservicos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.iridiumit.cmkservicos.modelos.Cliente;
import br.com.iridiumit.cmkservicos.modelos.Equipamento;

public interface Equipamentos extends JpaRepository<Equipamento, Long> {
	
	List<Equipamento> findByFabricanteContainingIgnoreCase(String fabricante);
	
	List<Equipamento> findByModeloContainingIgnoreCase(String modelo);
	
	Equipamento findByCliente(Cliente c);
	
	Equipamento findByNrcmk(Integer nr);

}
