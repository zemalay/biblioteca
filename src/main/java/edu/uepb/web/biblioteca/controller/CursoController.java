package edu.uepb.web.biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import edu.uepb.web.biblioteca.exception.AutenticacaoException;
import edu.uepb.web.biblioteca.exception.ExistException;
import edu.uepb.web.biblioteca.model.Curso;
import edu.uepb.web.biblioteca.model.Funcionario;
import edu.uepb.web.biblioteca.service.CursoService;

/**
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 */
@Controller
public class CursoController {

	@Autowired
	private CursoService cursoService;

	@RequestMapping(value = "/curso/add", method = RequestMethod.POST)
	public String cadastra(@SessionAttribute("funcionario") Funcionario funcionarioLogado,
			@ModelAttribute("curso") Curso curso, Model model) {
		try {
			cursoService.cadastraCurso(funcionarioLogado, curso);
		} catch (AutenticacaoException | ExistException e) {
			model.addAttribute("curso", new Curso());
			model.addAttribute("funcionario", funcionarioLogado);
			model.addAttribute("mensagem", e.getMessage());
		}
		return "redirect:/cursos";
	}

	@RequestMapping(value = "/curso/form", method = RequestMethod.GET)
	public String getCursoForm(@SessionAttribute("funcionario") Funcionario funcionarioLogado, Model model) {
		model.addAttribute("funcionario", funcionarioLogado);
		model.addAttribute("curso", new Curso());
		return "cursoForm";
	}

	@RequestMapping(value = "/cursos", method = RequestMethod.GET)
	public String getListaItem(@SessionAttribute("funcionario") Funcionario funcionarioLogado, Model model) {
		model.addAttribute("funcionario", funcionarioLogado);
		model.addAttribute("listaCurso", cursoService.getListaCurso());
		return "listaCurso";
	}

//	@RequestMapping(value = "/curso/{id}", method = RequestMethod.GET)
//	public String getItem(@SessionAttribute("funcionario") Funcionario funcionarioLogado,
//			@PathVariable("id") int idCurso, Model model) {
//		model.addAttribute("curso", cursoService.getCursoById(idCurso));
//		model.addAttribute("funcionario", funcionarioLogado);
//		return "cursoDetail";
//	}

	@RequestMapping(value = "/curso/delete/{id}", method = RequestMethod.GET)
	public String removerFuncionario(@SessionAttribute("funcionario") Funcionario funcionarioLogado,
			@PathVariable("id") int idCurso, Model model) {
		Curso curso = cursoService.getCursoById(idCurso);
		try {
			cursoService.removerCurso(funcionarioLogado, curso);
		} catch (AutenticacaoException e) {
			model.addAttribute("listaCurso", cursoService.getListaCurso());
			model.addAttribute("mensagem", e.getMessage());
			return "listaCurso";
		}
		return "redirect:/cursos";
	}

}
