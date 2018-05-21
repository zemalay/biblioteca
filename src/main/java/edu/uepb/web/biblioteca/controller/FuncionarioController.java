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
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import edu.uepb.web.biblioteca.exception.AutenticacaoException;
import edu.uepb.web.biblioteca.model.Aluno;
import edu.uepb.web.biblioteca.model.Funcionario;
import edu.uepb.web.biblioteca.service.FuncionarioService;

/**
 * Controller do Funcionario, oferece as rotas para carregar as paginas de
 * acordo com as suas operacoes do Funcionario
 * 
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 */
@Controller
@SessionAttributes("funcionarioLogado")
@RequestMapping("/")
public class FuncionarioController {

	@Autowired
	private FuncionarioService funcionarioService;

	@ModelAttribute("funcionarioLogado")
	public Funcionario setUpUserForm() {
		return new Funcionario();
	}

	/**
	 * Carrega a primeira pagina do sistema
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("funcionario", new Funcionario());
		model.addAttribute("aluno", new Aluno());
		return "index";
	}

	/**
	 * Sair do sistema (Log Out)
	 * 
	 * @param request
	 * @param status
	 * @return
	 */
	@RequestMapping(value = "/sair", method = RequestMethod.GET)
	public String sair(WebRequest request, SessionStatus status) {
		status.setComplete();
		request.removeAttribute("funcionarioLogado", WebRequest.SCOPE_SESSION);
		return "redirect:/";
	}

	/**
	 * Autentica o Funcionario
	 * 
	 * @param funcionario
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/funcionario/auth", method = RequestMethod.POST)
	public String autenticar(@ModelAttribute("funcionario") Funcionario funcionario, Model model) {
		Funcionario funcionarioLogado;
		try {
			funcionarioLogado = funcionarioService.autenticar(funcionario.getUsuario(), funcionario.getSenha());
			model.addAttribute("funcionarioLogado", funcionarioLogado);
		} catch (AutenticacaoException e) {
			model.addAttribute("aluno", new Aluno());
			model.addAttribute("funcionario", new Funcionario());
			model.addAttribute("mensagem", e.getMessage());
			return "index";
		}
		return "redirect:/home";
	}

	/**
	 * cadastra o Funcinario novo no Sistema
	 * 
	 * @param funcionario
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/funcionario/add", method = RequestMethod.POST)
	public String cadastra(@ModelAttribute("funcionario") Funcionario funcionario, Model model) {
		funcionarioService.cadastrarFuncionario(funcionario);
		return "redirect:/funcionarios";
	}

	/**
	 * Atualiza o Funcionario
	 * 
	 * @param funcionario
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/funcionario/update", method = RequestMethod.POST)
	public String atualizar(@ModelAttribute("funcionario") Funcionario funcionario, Model model) {
		funcionarioService.atualizarFuncionario(funcionario);
		return "redirect:/funcionarios";
	}

	/**
	 * Carrega a pagina para listar todos os Funcionarios cadastrdos no sistema
	 * 
	 * @param funcionarioLogado
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/funcionarios", method = RequestMethod.GET)
	public String getListaFuncionario(@SessionAttribute("funcionarioLogado") Funcionario funcionarioLogado,
			Model model) {
		model.addAttribute("listaFuncionario", funcionarioService.getListaFuncionario());
		model.addAttribute("funcionarioLogado", funcionarioLogado);
		return "listaFuncionario";
	}

	/**
	 * Carrega o formulario do Funcionario
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/funcionario/form", method = RequestMethod.GET)
	public String getFuncForm(Model model) {
		model.addAttribute("funcionario", new Funcionario());
		return "funcionarioForm";
	}

	/**
	 * Carrega detalhes de um Funcionario pelo seu ID
	 * 
	 * @param idFuncionario
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/funcionario/{id}", method = RequestMethod.GET)
	public String getFuncionario(@PathVariable("id") int idFuncionario, Model model) {
		model.addAttribute("funcionario", funcionarioService.getFuncionarioById(idFuncionario));
		return "funcionarioDetail";
	}

	/**
	 * Remover Funcionario pelo seu ID
	 * 
	 * @param idFuncionario
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/funcionario/delete/{id}", method = RequestMethod.GET)
	public String removerFuncionario(@PathVariable("id") int idFuncionario, Model model) {
		Funcionario funcionario = funcionarioService.getFuncionarioById(idFuncionario);
		funcionarioService.deletarFuncionario(funcionario);
		return "redirect:/funcionarios";
	}

	/**
	 * Retornar o funcionario de acordo com o usuario digita Autocompleto do Curso
	 * 
	 * @param funcionarioNome
	 * @return List<Funcionario>
	 */
	@RequestMapping(value = "/getFuncionarios", method = RequestMethod.GET)
	public @ResponseBody List<Funcionario> getFuncionario(@RequestParam String funcionarioNome) {
		List<Funcionario> resultado = new ArrayList<Funcionario>();
		List<Funcionario> listaFuncionario = funcionarioService.getListaFuncionario();

		for (Funcionario obj : listaFuncionario) {
			if (obj.getNome().toLowerCase().contains(funcionarioNome.toLowerCase())) {
				resultado.add(obj);
			}
		}
		return resultado;
	}
}