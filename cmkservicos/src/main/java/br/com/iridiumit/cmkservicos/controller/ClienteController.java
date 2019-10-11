package br.com.iridiumit.cmkservicos.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.iridiumit.cmkservicos.modelos.Cliente;
import br.com.iridiumit.cmkservicos.modelos.Endereco;
import br.com.iridiumit.cmkservicos.relatorio.ClienteREL;
import br.com.iridiumit.cmkservicos.repository.Clientes;
import br.com.iridiumit.cmkservicos.repository.Enderecos;
import br.com.iridiumit.cmkservicos.repository.Equipamentos;
import br.com.iridiumit.cmkservicos.repository.filtros.FiltroGeral;

@Controller
@RequestMapping("/administracao/clientes")
public class ClienteController {

	@Autowired
	private Clientes clientes;

	@Autowired
	private Equipamentos equipamentos;

	@Autowired
	private Enderecos enderecos;

	@GetMapping
	public ModelAndView listar(@ModelAttribute("filtro") FiltroGeral filtro) {

		ModelAndView modelAndView = new ModelAndView("administracao/cliente/lista-clientes");

		if (filtro.getTextoFiltro() == null) {
			modelAndView.addObject("clientes", clientes.findAll());
		} else {
			modelAndView.addObject("clientes",
					clientes.findByNomeContainingIgnoreCaseAndAtivo(filtro.getTextoFiltro(), true));
		}

		return modelAndView;
	}

	@GetMapping("/{id}")
	public ModelAndView clienteEquipamentos(@PathVariable Integer id) {

		ModelAndView modelAndView = new ModelAndView("administracao/cliente/lista-cliente-e-equipamentos");

		Cliente c = clientes.getOne(id);

		modelAndView.addObject(c);

		modelAndView.addObject("equipamentos", equipamentos.findByCliente(c));

		modelAndView.addObject("mensagem", "Cliente salvo com sucesso!");

		return modelAndView;
	}

	@GetMapping("/selecao/{id}")
	public ModelAndView SelecaoPorCliente(@PathVariable Integer id, @ModelAttribute("filtro") FiltroGeral filtro) {

		Cliente c = clientes.getOne(id);

		ModelAndView modelAndView = new ModelAndView("administracao/cliente/lista-cliente-e-equipamentos");

		modelAndView.addObject(c);

		modelAndView.addObject("equipamentos", equipamentos.findByCliente(c));

		return modelAndView;

	}

	@DeleteMapping("excluir/{id}")
	public String excluir(@PathVariable Integer id, RedirectAttributes attributes) {

		Cliente c = clientes.getOne(id);

		c.setAtivo(false); // Inativa o cliente na base de dados, mas mantem as informações de cadastro

		clientes.save(c);

		attributes.addFlashAttribute("mensagem", "Cliente inativado com sucesso!!");

		return "redirect:/atendimento/clientes";
	}

	@GetMapping("editar/{id}")
	public ModelAndView editar(@PathVariable Integer id) {

		return novo(clientes.getOne(id));
	}

	@GetMapping("ativar/{id}")
	public String ativar(@PathVariable Integer id, RedirectAttributes attributes) {

		Cliente c = clientes.getOne(id);

		c.setAtivo(true);

		clientes.save(c);

		attributes.addFlashAttribute("mensagem", "Cliente re-ativado com sucesso!");

		return "redirect:/administracao/clientes";

	}

	@GetMapping("inativar/{id}")
	public String inativar(@PathVariable Integer id, RedirectAttributes attributes) {

		Cliente c = clientes.getOne(id);

		c.setAtivo(false);

		clientes.save(c);

		attributes.addFlashAttribute("mensagem", "Cliente inativado com sucesso!");

		return "redirect:/administracao/clientes";

	}

	@GetMapping("/novo")
	public ModelAndView novo(Cliente cliente) {

		ModelAndView modelAndView = new ModelAndView("administracao/cliente/cadastro-cliente");

		modelAndView.addObject(cliente);

		return modelAndView;
	}

	@PostMapping("/salvar")
	public ModelAndView salvar(@Valid Cliente cliente, BindingResult result, RedirectAttributes attributes) {

		Cliente c = clientes.findByEmail(cliente.getEmail());

		Endereco e = cliente.getEndereco();

		if (c != null) {
			if (!cliente.getId().equals(c.getId())) {

				result.rejectValue("email", "email.existente");
			}
		}

		if (result.hasErrors()) {
			return novo(cliente);
		}

		if (e.getNr().isEmpty()) {
			e.setNr("S/N");
		}

		enderecos.save(e);

		cliente.setEndereco(e);

		cliente.setAtivo(true);

		clientes.save(cliente);

		attributes.addFlashAttribute("mensagem", "Cliente salvo com sucesso!!");

		return new ModelAndView("redirect:/administracao/clientes/" + cliente.getId());

	}

	@GetMapping(value = "/rel-clientes", produces = MediaType.APPLICATION_PDF_VALUE)
	public @ResponseBody byte[] getRelClientes() throws IOException {

		ClienteREL relatorio = new ClienteREL();

		try {
			relatorio.imprimir(clientes.findAll());
		} catch (Exception e) {
			e.printStackTrace();
		}

		InputStream in = this.getClass().getResourceAsStream("/relatorios/Relatorio_de_Clientes.pdf");
		return IOUtils.toByteArray(in);

	}

}
