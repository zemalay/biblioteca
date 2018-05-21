package edu.uepb.web.biblioteca.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import edu.uepb.web.biblioteca.exception.AutenticacaoException;
import edu.uepb.web.biblioteca.exception.ExistException;
import edu.uepb.web.biblioteca.model.Curso;
import edu.uepb.web.biblioteca.model.Funcionario;
import edu.uepb.web.biblioteca.service.CursoService;

/**
 * Controller do Curso, carregar as paginas do curso e realizar operacoes
 * 
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 */
@Controller
public class CursoController {

	@Autowired
	private CursoService cursoService;

	/**
	 * Funcionario cadastrar Curso
	 * 
	 * @param funcionarioLogado
	 * @param curso
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/curso/add", method = RequestMethod.POST)
	public String cadastraCurso(@SessionAttribute("funcionarioLogado") Funcionario funcionarioLogado,
			@ModelAttribute("curso") Curso curso, Model model) {
		try {
			cursoService.cadastraCurso(funcionarioLogado, curso);
		} catch (AutenticacaoException | ExistException e) {
			model.addAttribute("curso", new Curso());
			model.addAttribute("funcionarioLogado", funcionarioLogado);
			model.addAttribute("mensagem", e.getMessage());
		}
		return "redirect:/cursos";
	}

	/**
	 * Carregar formulario para cadastrar Curso
	 * 
	 * @param funcionarioLogado
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/curso/form", method = RequestMethod.GET)
	public String getCursoForm(@SessionAttribute("funcionarioLogado") Funcionario funcionarioLogado, Model model) {
		model.addAttribute("funcionarioLogado", funcionarioLogado);
		model.addAttribute("curso", new Curso());
		return "cursoForm";
	}

	/**
	 * Carregar a pagina para listar todos os Cursos cadastrados
	 * 
	 * @param funcionarioLogado
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/cursos", method = RequestMethod.GET)
	public String getListaCurso(@SessionAttribute("funcionarioLogado") Funcionario funcionarioLogado, Model model) {
		model.addAttribute("funcionarioLogado", funcionarioLogado);
		model.addAttribute("listaCurso", cursoService.getListaCurso());
		return "listaCurso";
	}

	/**
	 * Retornar o curso de acordo com o usuario digita Autocompleto do Curso
	 * 
	 * @param nome
	 * @return List<Curso>
	 */
	@RequestMapping(value = "/getCursos", method = RequestMethod.GET)
	public @ResponseBody List<Curso> getCurso(@RequestParam String cursoNome) {
		List<Curso> resultado = new ArrayList<Curso>();
		List<Curso> listaCurso = cursoService.getListaCurso();

		for (Curso obj : listaCurso) {
			if (obj.getNome().toLowerCase().contains(cursoNome.toLowerCase())) {
				resultado.add(obj);
			}
		}
		return resultado;
	}

	/**
	 * Funcionario deletar o Curso pelo seu ID
	 * 
	 * @param funcionarioLogado
	 * @param idCurso
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/curso/delete/{id}", method = RequestMethod.GET)
	public String removerCurso(@SessionAttribute("funcionarioLogado") Funcionario funcionarioLogado,
			@PathVariable("id") int idCurso, Model model) {
		Curso curso = cursoService.getCursoById(idCurso);
		try {
			cursoService.removerCurso(funcionarioLogado, curso);
		} catch (AutenticacaoException e) {
			model.addAttribute("funcionarioLogado", funcionarioLogado);
			model.addAttribute("listaCurso", cursoService.getListaCurso());
			model.addAttribute("mensagem", e.getMessage());
			return "listaCurso";
		}
		return "redirect:/cursos";
	}

}
