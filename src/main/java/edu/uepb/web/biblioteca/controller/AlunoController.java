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
import edu.uepb.web.biblioteca.model.Aluno;
import edu.uepb.web.biblioteca.model.Funcionario;
import edu.uepb.web.biblioteca.service.AlunoService;
import edu.uepb.web.biblioteca.service.CursoService;

/**
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 */
@Controller
public class AlunoController {

	@Autowired
	private AlunoService alunoService;

	@Autowired
	private CursoService cursoService;

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

	@RequestMapping(value = "/aluno/add", method = RequestMethod.POST)
	public String cadastraAluno(@SessionAttribute("funcionario") Funcionario funcionarioLogado,
			@ModelAttribute("aluno") Aluno aluno, Model model) {
		aluno.setCurso(cursoService.getCursoById(aluno.getCurso().getId()));
		try {
			alunoService.cadastrarAluno(funcionarioLogado, aluno);
		} catch (AutenticacaoException | ExistException e) {
			model.addAttribute("aluno", new Aluno());
			model.addAttribute("funcionario", funcionarioLogado);
			model.addAttribute("mensagem", e.getMessage());
			return "alunoForm";
		}
		return "redirect:/alunos";
	}

	@RequestMapping(value = "/aluno/form", method = RequestMethod.GET)
	public String getAlunoForm(@SessionAttribute("funcionario") Funcionario funcionarioLogado, Model model) {
		model.addAttribute("funcionario", funcionarioLogado);
		model.addAttribute("aluno", new Aluno());
		return "alunoForm";
	}

	@RequestMapping(value = "/alunos", method = RequestMethod.GET)
	public String getListaAluno(@SessionAttribute("funcionario") Funcionario funcionarioLogado, Model model) {
		model.addAttribute("funcionario", funcionarioLogado);
		model.addAttribute("listaAluno", alunoService.getListaAluno());
		return "listaAluno";
	}

	@RequestMapping(value = "/aluno/{id}", method = RequestMethod.GET)
	public String getAluno(@SessionAttribute("funcionario") Funcionario funcionarioLogado,
			@PathVariable("id") int idAluno, Model model) {
		model.addAttribute("aluno", alunoService.getAlunoById(idAluno));
		model.addAttribute("funcionario", funcionarioLogado);
		return "alunoDetail";
	}

	@RequestMapping(value = "/aluno/delete/{id}", method = RequestMethod.GET)
	public String removerAlunos(@SessionAttribute("funcionario") Funcionario funcionarioLogado,
			@PathVariable("id") int idAluno, Model model) {
		Aluno aluno = alunoService.getAlunoById(idAluno);
		try {
			alunoService.removerAluno(funcionarioLogado, aluno);
		} catch (AutenticacaoException e) {
			model.addAttribute("listaAluno", alunoService.getListaAluno());
			model.addAttribute("mensagem", e.getMessage());
			return "listaAluno";
		}
		return "redirect:/alunos";
	}

	@RequestMapping(value = "/aluno/update", method = RequestMethod.POST)
	public String atualizarAluno(@ModelAttribute("aluno") Aluno aluno, Model model) {
		alunoService.atualizarAluno(aluno);
		return "redirect:/alunos";
	}

	/**
	 * Retornar o aluno de acordo com usuario digita - Autocomplete Aluno
	 * 
	 * @param alunoNome
	 * @return List<Aluno>
	 */
	@RequestMapping(value = "/getAlunos", method = RequestMethod.GET)
	public @ResponseBody List<Aluno> getAluno(@RequestParam String alunoNome) {
		List<Aluno> resultado = new ArrayList<Aluno>();
		List<Aluno> listaAluno = alunoService.getListaAluno();

		for (Aluno obj : listaAluno) {
			if (obj.getNome().toLowerCase().contains(alunoNome)) {
				resultado.add(obj);
			}
		}
		return resultado;
	}

}
