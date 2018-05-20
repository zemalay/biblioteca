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
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.uepb.web.biblioteca.exception.AutenticacaoException;
import edu.uepb.web.biblioteca.exception.EmprestimoException;
import edu.uepb.web.biblioteca.exception.ExistException;
import edu.uepb.web.biblioteca.model.Aluno;
import edu.uepb.web.biblioteca.model.Emprestimo;
import edu.uepb.web.biblioteca.model.Funcionario;
import edu.uepb.web.biblioteca.model.Reserva;
import edu.uepb.web.biblioteca.service.AlunoService;
import edu.uepb.web.biblioteca.service.CursoService;
import edu.uepb.web.biblioteca.service.DividaService;
import edu.uepb.web.biblioteca.service.EmprestimoService;
import edu.uepb.web.biblioteca.service.ItemService;
import edu.uepb.web.biblioteca.service.ReservaService;

/**
 * Controller do Aluno
 * 
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 */
@Controller
@SessionAttributes("aluno")
public class AlunoController {

	@Autowired
	private AlunoService alunoService;

	@Autowired
	private CursoService cursoService;

	@Autowired
	private EmprestimoService emprestimoService;

	@Autowired
	private DividaService dividaService;

	@Autowired
	private ReservaService reservaService;

	@Autowired
	private ItemService itemService;

	@ModelAttribute("aluno")
	public Aluno setUpUserForm() {
		return new Aluno();
	}

	/**
	 * Autenticacao do aluno
	 * 
	 * @param aluno
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/aluno/auth", method = RequestMethod.POST)
	public String autenticar(@ModelAttribute("aluno") Aluno aluno, Model model) {
		Aluno alunoLogado;

		try {
			alunoLogado = alunoService.autenticar(aluno.getMatricula(), aluno.getSenha());
			model.addAttribute("aluno", alunoLogado);
		} catch (AutenticacaoException e) {
			model.addAttribute("aluno", new Aluno());
			model.addAttribute("mensagem", e.getMessage());
			return "index";
		}
		return "redirect:/aluno/home";
	}

	/**
	 * Carregar a pagina Home do Aluno
	 * 
	 * @param alunoLogado
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/aluno/home", method = RequestMethod.GET)
	public String homeAluno(@SessionAttribute("aluno") Aluno alunoLogado, Model model) {
		model.addAttribute("aluno", alunoLogado);
		model.addAttribute("listaEmprestimo", emprestimoService.getEmprestimoByAluno(alunoLogado.getId()));
		model.addAttribute("listaDivida", dividaService.getListaDividaByAluno(alunoLogado.getId()));
		model.addAttribute("listaReserva", reservaService.getListaReservaByAluno(alunoLogado.getId()));
		return "homeAluno";
	}

	/**
	 * 
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/aluno/itens", method = RequestMethod.GET)
	public String alunoGetItens(Model model) {
		model.addAttribute("listaItem", itemService.getListaItem());
		return "listaItemAluno";
	}
	
	@RequestMapping(value = "/aluno/reserva/add", method = RequestMethod.POST)
	public String alunoCadastraReserva(@ModelAttribute("reserva") Reserva reserva, Model model) {
		try {
			reservaService.reservaItem(reserva.getAluno().getId(), reserva.getItem().getId(), reserva.isEmail());
		} catch (EmprestimoException e) {
			model.addAttribute("reserva", reserva);
			model.addAttribute("mensagem", e.getMessage());
			return "alunoReservaForm";
		}
		return "redirect:/aluno/home";
	}

	@RequestMapping(value = "/aluno/reserva/form", method = RequestMethod.GET)
	public String getAlunoReservaForm(@SessionAttribute("aluno") Aluno alunoLogado, Model model) {
		System.err.println(alunoLogado);
		Reserva reserva = new Reserva();
		reserva.setAluno(alunoLogado);
		model.addAttribute("reserva", reserva);
		model.addAttribute("aluno", alunoLogado);
		return "alunoReservaForm";
	}


	@RequestMapping(value = "/aluno/emprestimo/renovar/{id}", method = RequestMethod.GET)
	public String renovarEmprestimo(@SessionAttribute("aluno") Aluno alunoLogado, @PathVariable("id") int idEmprestimo,
			Model model) {
		Emprestimo emprestimo = emprestimoService.getEmprestimo(idEmprestimo);
		try {
			emprestimoService.renovarEmprestimo(emprestimo.getAluno().getId(), emprestimo.getId());
		} catch (EmprestimoException e) {
			model.addAttribute("aluno", alunoLogado);
			model.addAttribute("listaEmprestimo", emprestimoService.getListaEmprestimo());
			model.addAttribute("listaDivida", dividaService.getListaDivida());
			model.addAttribute("mensagem", e.getMessage());
			return "homeAluno";
		}
		return "redirect:/homeAluno";
	}

	@RequestMapping(value = "/aluno/perfil", method = RequestMethod.GET)
	public String perfilAluno(@SessionAttribute("aluno") Aluno alunoLogado, Model model) {
		model.addAttribute("aluno", alunoLogado);
		return "alunoPerfil";
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
		model.addAttribute("listaEmprestimo", emprestimoService.getEmprestimoByAluno(idAluno));
		model.addAttribute("listaDivida", dividaService.getListaDividaByAluno(idAluno));
		model.addAttribute("listaReserva", reservaService.getListaReservaByAluno(idAluno));
		model.addAttribute("funcionario", funcionarioLogado);
		return "alunoDetail";
	}

	@RequestMapping(value = "/aluno/delete/{id}", method = RequestMethod.GET)
	public String removerAlunos(@SessionAttribute("funcionario") Funcionario funcionarioLogado,
			@PathVariable("id") int idAluno, Model model) {
		Aluno aluno = alunoService.getAlunoById(idAluno);
		try {
			alunoService.removerAluno(funcionarioLogado, aluno);
		} catch (AutenticacaoException | EmprestimoException e) {
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
