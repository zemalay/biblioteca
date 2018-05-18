package edu.uepb.web.biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.uepb.web.biblioteca.exception.AutenticacaoException;
import edu.uepb.web.biblioteca.model.Aluno;
import edu.uepb.web.biblioteca.model.Funcionario;
import edu.uepb.web.biblioteca.service.AlunoService;

/**
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 */
@Controller
public class AlunoController {
	
	@Autowired
	private AlunoService alunoService;
	
	@RequestMapping(value = "/aluno/auth", method = RequestMethod.POST)
	public String autenticar(@ModelAttribute("aluno") Aluno aluno, Model model) {
		Aluno alunoLogado;
		
		try {
			alunoLogado = alunoService.autenticar(aluno.getMatricula(), aluno.getSenha());
			model.addAttribute("funcionario", alunoLogado);
		} catch (AutenticacaoException e) {
			model.addAttribute("funcionario", new Funcionario());
			model.addAttribute("mensagem", e.getMessage());
			return "index";
		}
		return "redirect:/aluno/home";
	}
}

