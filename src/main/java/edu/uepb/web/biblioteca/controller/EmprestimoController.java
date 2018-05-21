package edu.uepb.web.biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import edu.uepb.web.biblioteca.exception.EmprestimoException;
import edu.uepb.web.biblioteca.model.Emprestimo;
import edu.uepb.web.biblioteca.model.Funcionario;
import edu.uepb.web.biblioteca.service.DividaService;
import edu.uepb.web.biblioteca.service.EmprestimoService;

/**
 * Controller do Emprestimo, oferece as rotas para carregar as paginas de acordo
 * com as suas operacoes do Emprestimo
 * 
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 */
@Controller
public class EmprestimoController {

	@Autowired
	private EmprestimoService emprestimoService;

	@Autowired
	private DividaService dividaService;

	/**
	 * Carregar a pagina home com os emprestimos ativos
	 * 
	 * @param funcionarioLogado
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(@SessionAttribute("funcionarioLogado") Funcionario funcionarioLogado, Model model) {
		model.addAttribute("funcionarioLogado", funcionarioLogado);
		model.addAttribute("listaEmprestimo", emprestimoService.getListaEmprestimo());
		model.addAttribute("listaDivida", dividaService.getListaDivida());
		return "home";
	}

	/**
	 * Funcionario registra emprestimo do Item do Aluno
	 * 
	 * @param emprestimo
	 * @param model
	 * @return
	 */
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


	/**
	 * Carregar o formulario do emprestimo String
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/emprestimo/form", method = RequestMethod.GET)
	public String getEmprestimoForm(Model model) {
		model.addAttribute("emprestimo", new Emprestimo());
		return "emprestimoForm";
	}

	/**
	 * Carrega pagina para listar todos os Emprestimos registrados
	 * 
	 * @param funcionarioLogado
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/emprestimos", method = RequestMethod.GET)
	public String getListaEmprestimo(@SessionAttribute("funcionarioLogado") Funcionario funcionarioLogado,
			Model model) {
		model.addAttribute("funcionarioLogado", funcionarioLogado);
		model.addAttribute("listaEmprestimo", emprestimoService.getListaEmprestimoAll());
		return "listaEmprestimos";
	}

	/**
	 * Funcionario renovar o Emprestimo de um Aluno dado ID pela URL
	 * 
	 * @param funcionarioLogado
	 * @param idEmprestimo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/emprestimo/renovar/{id}", method = RequestMethod.GET)
	public String renovarEmprestimo(@SessionAttribute("funcionarioLogado") Funcionario funcionarioLogado,
			@PathVariable("id") int idEmprestimo, Model model) {
		Emprestimo emprestimo = emprestimoService.getEmprestimo(idEmprestimo);
		try {
			emprestimoService.renovarEmprestimo(emprestimo.getAluno().getId(), emprestimo.getId());
		} catch (EmprestimoException e) {
			model.addAttribute("funcionarioLogado", funcionarioLogado);
			model.addAttribute("listaEmprestimo", emprestimoService.getListaEmprestimo());
			model.addAttribute("listaDivida", dividaService.getListaDivida());
			model.addAttribute("mensagem", e.getMessage());
			return "home";
		}
		return "redirect:/home";
	}

	/**
	 * Funcionario confirma a entrega de um Item
	 * 
	 * @param funcionarioLogado
	 * @param idEmprestimo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/emprestimo/entregar/{id}", method = RequestMethod.GET)
	public String entregaEmprestimo(@SessionAttribute("funcionarioLogado") Funcionario funcionarioLogado,
			@PathVariable("id") int idEmprestimo, Model model) {
		emprestimoService.devolucaoEmprestimo(idEmprestimo);
		model.addAttribute("funcionarioLogado", funcionarioLogado);
		model.addAttribute("listaEmprestimo", emprestimoService.getListaEmprestimo());
		model.addAttribute("listaDivida", dividaService.getListaDivida());
		return "home";
	}


}
