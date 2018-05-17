package edu.uepb.web.biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.uepb.web.biblioteca.exception.AutenticacaoException;
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

	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("funcionario", new Funcionario());
		return "default";
	}

	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public String autenticar(@Validated @ModelAttribute("funcionario") Funcionario funcionario, BindingResult result,
			ModelMap model) {
		Funcionario funcionarioLogado;
		try {
			funcionarioLogado = service.autenticar(funcionario.getUsuario(), funcionario.getSenha());
			model.addAttribute("funcionario", funcionarioLogado);
			return "home";
		} catch (AutenticacaoException e) {
			model.addAttribute("mensagem", e);
			return "login";
		}

	}
}