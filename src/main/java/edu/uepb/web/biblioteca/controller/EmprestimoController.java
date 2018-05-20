package edu.uepb.web.biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import edu.uepb.web.biblioteca.exception.EmprestimoException;
import edu.uepb.web.biblioteca.model.Emprestimo;
import edu.uepb.web.biblioteca.model.Funcionario;
import edu.uepb.web.biblioteca.service.EmprestimoService;

/**
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 */
@Controller
public class EmprestimoController {

	@Autowired
	private EmprestimoService emprestimoService;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(@SessionAttribute("funcionario") Funcionario funcionarioLogado, Model model) {
		model.addAttribute("funcionario", funcionarioLogado);
		model.addAttribute("listaEmprestimo", emprestimoService.getListaEmprestimo());
		return "home";
	}

	@RequestMapping(value = "/emprestimo/add", method = RequestMethod.POST)
	public String cadastraEmprestimo(@ModelAttribute("emprestimo") Emprestimo emprestimo, Model model) {
		try {
			emprestimoService.cadastrarEmprestimo(emprestimo.getFuncionario().getId(), emprestimo.getAluno().getId(),
					emprestimo.getItem().getId());
		} catch (EmprestimoException e) {
			model.addAttribute("emprestimo", emprestimo);
			model.addAttribute("mensagem", e.getMessage());
			return "emprestimoForm";
		}
		return "redirect:/emprestimos";
	}

	@RequestMapping(value = "/emprestimo/form", method = RequestMethod.GET)
	public String getEmprestimoForm(Model model) {
		model.addAttribute("emprestimo", new Emprestimo());
		return "emprestimoForm";
	}

	@RequestMapping(value = "/emprestimos", method = RequestMethod.GET)
	public String getListaEmprestimo(@SessionAttribute("funcionario") Funcionario funcionarioLogado, Model model) {
		model.addAttribute("funcionario", funcionarioLogado);
		model.addAttribute("listaEmprestimo", emprestimoService.getListaEmprestimoAll());
		return "home";
	}
}
