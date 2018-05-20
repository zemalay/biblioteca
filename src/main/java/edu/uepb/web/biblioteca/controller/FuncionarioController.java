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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import edu.uepb.web.biblioteca.exception.AutenticacaoException;
import edu.uepb.web.biblioteca.model.Aluno;
import edu.uepb.web.biblioteca.model.Funcionario;
import edu.uepb.web.biblioteca.service.FuncionarioService;

/**
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 */
@Controller
@SessionAttributes("funcionario")
@RequestMapping("/")
public class FuncionarioController {

	@Autowired
	private FuncionarioService funcionarioService;

	@ModelAttribute("funcionario")
	public Funcionario setUpUserForm() {
		return new Funcionario();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("funcionario", new Funcionario());
		model.addAttribute("aluno", new Aluno());
		return "index";
	}
	
	@RequestMapping(value = "/sair", method = RequestMethod.GET)
	public String sair(WebRequest request, SessionStatus status) {
	    status.setComplete();
	    request.removeAttribute("funcionario", WebRequest.SCOPE_SESSION);
	    request.removeAttribute("user", WebRequest.SCOPE_SESSION);
	    return "redirect:/";
	}

	@RequestMapping(value = "/funcionario/auth", method = RequestMethod.POST)
	public String autenticar(@ModelAttribute("funcionario") Funcionario funcionario, Model model) {
		Funcionario funcionarioLogado;
		try {
			funcionarioLogado = funcionarioService.autenticar(funcionario.getUsuario(), funcionario.getSenha());
			model.addAttribute("funcionario", funcionarioLogado);
		} catch (AutenticacaoException e) {
			model.addAttribute("aluno", new Aluno());
			model.addAttribute("mensagem", e.getMessage());
			return "index";
		}
		return "redirect:/home";
	}

	@RequestMapping(value = "/funcionario/add", method = RequestMethod.POST)
	public String cadastra(@ModelAttribute("funcionario") Funcionario funcionario, Model model) {
		funcionarioService.cadastrarFuncionario(funcionario);
		return "redirect:/funcionarios";
	}

	@RequestMapping(value = "/funcionario/update", method = RequestMethod.POST)
	public String atualizar(@ModelAttribute("funcionario") Funcionario funcionario, Model model) {
		funcionarioService.atualizarFuncionario(funcionario);
		return "redirect:/funcionarios";
	}

	@RequestMapping(value = "/funcionarios", method = RequestMethod.GET)
	public String getListaFuncionario(Model model) {
		model.addAttribute("listaFuncionario", funcionarioService.getListaFuncionario());
		return "listaFuncionario";
	}

	@RequestMapping(value = "/funcionario/form", method = RequestMethod.GET)
	public String getFuncForm(Model model) {
		model.addAttribute("funcionario", new Funcionario());
		return "funcionarioForm";
	}

	@RequestMapping(value = "/funcionario/{id}", method = RequestMethod.GET)
	public String getFuncionario(@PathVariable("id") int idFuncionario, Model model) {
		model.addAttribute("funcionario", funcionarioService.getFuncionarioById(idFuncionario));
		return "funcionarioDetail";
	}

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