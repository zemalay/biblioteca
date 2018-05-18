package edu.uepb.web.biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.uepb.web.biblioteca.exception.AutenticacaoException;
import edu.uepb.web.biblioteca.model.Aluno;
import edu.uepb.web.biblioteca.model.Funcionario;
import edu.uepb.web.biblioteca.service.FuncionarioService;

/**
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 */
@Controller
@RequestMapping("/")
public class FuncionarioController {
	@Autowired
	private FuncionarioService service;

	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("funcionario", new Funcionario());
		model.addAttribute("aluno", new Aluno());
		return "index";
	}

	@RequestMapping(value = "/funcionario/auth", method = RequestMethod.POST)
	public String autenticar(@ModelAttribute("funcionario") Funcionario funcionario, ModelMap model) {
		Funcionario funcionarioLogado;
		try {
			funcionarioLogado = service.autenticar(funcionario.getUsuario(), funcionario.getSenha());
			model.addAttribute("funcionario", funcionarioLogado);
		} catch (AutenticacaoException e) {
			model.addAttribute("aluno", new Aluno());
			model.addAttribute("mensagem", e.getMessage());
			return "index";
		}
		return "redirect:/funcionario/home";
	}
}