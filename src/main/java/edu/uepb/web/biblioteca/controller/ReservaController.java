package edu.uepb.web.biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import edu.uepb.web.biblioteca.exception.EmprestimoException;
import edu.uepb.web.biblioteca.model.Funcionario;
import edu.uepb.web.biblioteca.model.Reserva;
import edu.uepb.web.biblioteca.service.ReservaService;

/**
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 */
@Controller
public class ReservaController {

	@Autowired
	private ReservaService reservaService;

	@RequestMapping(value = "/reservas", method = RequestMethod.GET)
	public String getListaEmprestimo(@SessionAttribute("funcionario") Funcionario funcionarioLogado, Model model) {
		model.addAttribute("funcionario", funcionarioLogado);
		model.addAttribute("listaReserva", reservaService.getListaReserva());
		return "listaReserva";
	}

	@RequestMapping(value = "/reserva/add", method = RequestMethod.POST)
	public String cadastraReserva(@ModelAttribute("reserva") Reserva reserva, Model model) {
		try {
			System.err.println(reserva);
			reservaService.reservaItem(reserva.getAluno().getId(), reserva.getItem().getId(), reserva.isEmail());
		} catch (EmprestimoException e) {
			model.addAttribute("reserva", reserva);
			model.addAttribute("mensagem", e.getMessage());
			return "reservaForm";
		}
		return "redirect:/reservas";
	}

	@RequestMapping(value = "/reserva/form", method = RequestMethod.GET)
	public String getReservaForm(Model model) {
		model.addAttribute("reserva", new Reserva());
		return "reservaForm";
	}
}
