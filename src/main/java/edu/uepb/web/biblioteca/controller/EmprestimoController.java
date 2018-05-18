package edu.uepb.web.biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.uepb.web.biblioteca.service.EmprestimoService;

/**
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 */
@Controller
public class EmprestimoController {
	
	@Autowired
	private EmprestimoService emprestimoService;
	
	@RequestMapping(value = "/funcionario/home", method = RequestMethod.GET)
	public String home(Model model) {
		return "home";
	}
}

