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
import edu.uepb.web.biblioteca.model.Funcionario;
import edu.uepb.web.biblioteca.model.Reserva;
import edu.uepb.web.biblioteca.service.ReservaService;

/**
 * Controller da Reserva , oferece as rotas para carregar as paginas de acordo
 * com as suas operacoes da Reserva
 * 
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 */
@Controller
public class ReservaController {

	@Autowired
	private ReservaService reservaService;

	/**
	 * Carrega a pagina para listar todas as Reservas cadastrdas no sistema
	 * 
	 * @param funcionarioLogado
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/reservas", method = RequestMethod.GET)
	public String getListaEmprestimo(@SessionAttribute("funcionarioLogado") Funcionario funcionarioLogado,
			Model model) {
		model.addAttribute("funcionarioLogado", funcionarioLogado);
		model.addAttribute("listaReserva", reservaService.getListaReserva());
		return "listaReserva";
	}

	/**
	 * Registra nova reserva no sistema
	 * 
	 * @param funcionarioLogado
	 * @param reserva
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/reserva/add", method = RequestMethod.POST)
	public String cadastraReserva(@SessionAttribute("funcionarioLogado") Funcionario funcionarioLogado,
			@ModelAttribute("reserva") Reserva reserva, Model model) {
		try {
			reservaService.reservaItem(reserva.getAluno().getId(), reserva.getItem().getId(), reserva.isEmail());
		} catch (EmprestimoException e) {
			model.addAttribute("funcionarioLogado", funcionarioLogado);
			model.addAttribute("reserva", reserva);
			model.addAttribute("mensagem", e.getMessage());
			return "reservaForm";
		}
		return "redirect:/reservas";
	}

	/**
	 * Carregar o formulario da Reserva
	 * 
	 * @param funcionarioLogado
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/reserva/form", method = RequestMethod.GET)
	public String getReservaForm(@SessionAttribute("funcionarioLogado") Funcionario funcionarioLogado, Model model) {
		model.addAttribute("funcionarioLogado", funcionarioLogado);
		model.addAttribute("reserva", new Reserva());
		return "reservaForm";
	}

	/**
	 * Cancela a Reserva pelo ID dado
	 * 
	 * @param funcionarioLogado
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/reserva/cancelar/{id}", method = RequestMethod.GET)
	public String removerCurso(@SessionAttribute("funcionario") Funcionario funcionarioLogado,
			@PathVariable("id") int id, Model model) {
		reservaService.cancelarReserva(id);
		return "redirect:/reservas";
	}

}
