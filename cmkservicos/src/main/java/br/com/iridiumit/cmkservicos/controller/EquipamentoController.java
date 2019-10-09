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
import br.com.iridiumit.cmkservicos.modelos.Equipamento;
import br.com.iridiumit.cmkservicos.relatorio.EquipamentoREL;
import br.com.iridiumit.cmkservicos.repository.Clientes;
import br.com.iridiumit.cmkservicos.repository.Equipamentos;
import br.com.iridiumit.cmkservicos.repository.filtros.FiltroGeral;

@Controller
@RequestMapping("/administracao/clientes/equipamentos")
public class EquipamentoController {

	@Autowired
	private Equipamentos equipamentos;
	
	@Autowired
	private Clientes clientes;

	@GetMapping
	public ModelAndView listar(@ModelAttribute("filtro") FiltroGeral filtro) {

		ModelAndView modelAndView = new ModelAndView("administracao/equipamento/lista-equipamentos");

		if (filtro.getTextoFiltro() == null) {
			modelAndView.addObject("equipamentos", equipamentos.findAll());
		} else {
			modelAndView.addObject("equipamentos",
					equipamentos.findByFabricanteContainingIgnoreCase(filtro.getTextoFiltro()));
		}

		return modelAndView;
	}


	@DeleteMapping("excluir/{id}")
	public String excluir(@PathVariable Long id, RedirectAttributes attributes) {

		equipamentos.delete(equipamentos.getOne(id));

		attributes.addFlashAttribute("mensagem", "Equipamento excluido com sucesso!!");

		return "redirect:/administracao/clientes/equipamentos";
	}

	@GetMapping("editar/{id}")
	public ModelAndView editar(@PathVariable Long id) {

		return novo(equipamentos.getOne(id));
	}
	
	@GetMapping("/incluirEquipamento/{id}")
	public ModelAndView incluirEquipamento(@PathVariable Long id) {

		ModelAndView modelAndView = new ModelAndView("administracao/equipamento/cadastro-equipamento");

		Cliente c = clientes.getOne(id);

		Equipamento e = new Equipamento();

		e.setCliente(c);

		modelAndView.addObject(e);

		return modelAndView;
	}

	@GetMapping("/novo")
	public ModelAndView novo(Equipamento equipamento) {
		ModelAndView modelAndView = new ModelAndView("administracao/equipamento/cadastro-equipamento");

		modelAndView.addObject(equipamento);

		return modelAndView;
	}

	@PostMapping("/salvar")
	public ModelAndView salvar(@Valid Equipamento equipamento, BindingResult result, RedirectAttributes attributes) {

		Equipamento e = equipamentos.findByNrcmk(equipamento.getNrcmk());

		if (e != null && e.getId() != equipamento.getId()) {
			result.rejectValue("NrCMK", "nrcmk.existente");
		}

		if (result.hasErrors()) {
			return novo(equipamento);
		}

		equipamentos.save(equipamento);

		attributes.addFlashAttribute("mensagem", "Equipamento salvo com sucesso!!");

		return new ModelAndView("redirect:/administracao/clientes/equipamentos");

	}

	@GetMapping(value = "/rel-equipamentos", produces = MediaType.APPLICATION_PDF_VALUE)
	public @ResponseBody byte[] getRelClientes() throws IOException {

		EquipamentoREL relatorio = new EquipamentoREL();

		try {
			relatorio.imprimir(equipamentos.findAll());
		} catch (Exception e) {
			e.printStackTrace();
		}

		InputStream in = this.getClass().getResourceAsStream("/relatorios/Relatorio_de_equipamentos.pdf");
		return IOUtils.toByteArray(in);

	}

}
