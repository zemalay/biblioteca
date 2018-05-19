package edu.uepb.web.biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import edu.uepb.web.biblioteca.exception.AutenticacaoException;
import edu.uepb.web.biblioteca.exception.ExistException;
import edu.uepb.web.biblioteca.model.Funcionario;
import edu.uepb.web.biblioteca.model.Item;
import edu.uepb.web.biblioteca.service.ItemService;

/**
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 */
@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;

	@RequestMapping(value = "/item/add", method = RequestMethod.POST)
	public String cadastra(@SessionAttribute("funcionario") Funcionario funcionarioLogado, @ModelAttribute("item") Item item,
			Model model) {
		try {
			itemService.cadastraItem(funcionarioLogado, item);
		} catch (AutenticacaoException | ExistException e) {
			model.addAttribute("item", new Item());
			model.addAttribute("funcionario", funcionarioLogado);
			model.addAttribute("mensagem", e.getMessage());
			return "itemForm";
		}
		return "redirect:/itens";
	}
	
	@RequestMapping(value = "/item/form", method = RequestMethod.GET)
	public String getItemForm(@SessionAttribute("funcionario") Funcionario funcionarioLogado, Model model) {
		model.addAttribute("funcionario", funcionarioLogado);
		model.addAttribute("item", new Item());
		return "itemForm";
	}

	@RequestMapping(value = "/itens", method = RequestMethod.GET)
	public String getListaItem(@SessionAttribute("funcionario") Funcionario funcionarioLogado, Model model) {
		model.addAttribute("funcionario", funcionarioLogado);
		model.addAttribute("listaItem", itemService.getListaItem());
		return "listaItem";
	}
	@RequestMapping(value = "/item/{id}", method = RequestMethod.GET)
	public String getItem(@SessionAttribute("funcionario") Funcionario funcionarioLogado,@PathVariable("id") int idItem, Model model) {
		model.addAttribute("item", itemService.getItemById(idItem));
		model.addAttribute("funcionario", funcionarioLogado);
		return "itemDetail";
	}
	
	@RequestMapping(value = "/item/delete/{id}", method = RequestMethod.GET)
	public String removerFuncionario(@SessionAttribute("funcionario") Funcionario funcionarioLogado, @PathVariable("id") int idItem, Model model) {
		Item item = itemService.getItemById(idItem);
		try {
			itemService.removerItem(funcionarioLogado, item);
		} catch (AutenticacaoException e) {
			model.addAttribute("listaItem", itemService.getListaItem());
			model.addAttribute("mensagem", e.getMessage());
			return "listaItem";
		}
		return "redirect:/funcionarios";
	}
	
	@RequestMapping(value = "/item/update", method = RequestMethod.POST)
	public String atualizar(@SessionAttribute("funcionario") Funcionario funcionarioLogado,@ModelAttribute("item") Item item, Model model) {
		try {
			itemService.atualizarItem(funcionarioLogado, item);
		} catch (AutenticacaoException e) {
			model.addAttribute("item", new Item());
			model.addAttribute("funcionario", funcionarioLogado);
			model.addAttribute("mensagem", e.getMessage());
			return "itemDetail";
		}
		return "redirect:/itens";
	}
}
