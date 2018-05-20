package edu.uepb.web.biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import edu.uepb.web.biblioteca.model.Funcionario;
import edu.uepb.web.biblioteca.model.Universidade;
import edu.uepb.web.biblioteca.service.UniversidadeService;

/**
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 */
@Controller
public class UniversidadeController {

	@Autowired
	private UniversidadeService universidadeService;

	@RequestMapping(value = "/universidade/add", method = RequestMethod.POST)
	public String cadastra(@SessionAttribute("funcionario") Funcionario funcionarioLogado,
			@ModelAttribute("universidade") Universidade universidade, Model model) {
		if (universidade.getId() == 0) {
			universidadeService.cadastrarUniversidade(universidade);
			return "redirect:/universidade";
		}
		universidadeService.atualizarUniversidade(universidade);
		return "redirect:/universidade";

	}

	@RequestMapping(value = "/universidade", method = RequestMethod.GET)
	public String getAlunoForm(@SessionAttribute("funcionario") Funcionario funcionarioLogado, Model model) {
		model.addAttribute("funcionario", funcionarioLogado);
		if (universidadeService.getUniversidade() != null) {
			model.addAttribute("universidade", universidadeService.getUniversidade());
			return "universidadeForm";
		}
		model.addAttribute("universidade", new Universidade());
		return "universidadeForm";
	}
	
}
