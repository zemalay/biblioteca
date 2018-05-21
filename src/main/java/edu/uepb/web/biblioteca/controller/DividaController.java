package edu.uepb.web.biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.uepb.web.biblioteca.service.DividaService;

/**
 * Controller da Divida
 * 
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 */
@Controller
public class DividaController {

	@Autowired
	private DividaService dividaService;

	/**
	 * Funcionario confirmar o pagamento da divida do aluno
	 * 
	 * @param idDivida
	 * @return
	 */
	@RequestMapping(value = "/divida/pagar/{id}", method = RequestMethod.GET)
	public String pagarDivida(@PathVariable("id") int idDivida) {
		dividaService.pagarDividaAluno(idDivida);
		return "redirect:/home";
	}

}
