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
import edu.uepb.web.biblioteca.model.Funcionario;
import edu.uepb.web.biblioteca.model.Item;
import edu.uepb.web.biblioteca.service.ItemService;

/**
 * Controller do Item, oferece as rotas para carregar as paginas de acordo com
 * as suas operacoes do Item
 * 
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 */
@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;

	/**
	 * Funcionario registra o novo Item no Sistema
	 * 
	 * @param funcionarioLogado
	 * @param item
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/item/add", method = RequestMethod.POST)
	public String cadastra(@SessionAttribute("funcionarioLogado") Funcionario funcionarioLogado,
			@ModelAttribute("item") Item item, Model model) {
		try {
			itemService.cadastraItem(funcionarioLogado, item);
		} catch (AutenticacaoException | ExistException e) {
			model.addAttribute("item", new Item());
			model.addAttribute("funcionarioLogado", funcionarioLogado);
			model.addAttribute("mensagem", e.getMessage());
			return "itemForm";
		}
		return "redirect:/itens";
	}

	/**
	 * Carregar o formulario para registrar novo Item
	 * 
	 * @param funcionarioLogado
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/item/form", method = RequestMethod.GET)
	public String getItemForm(@SessionAttribute("funcionarioLogado") Funcionario funcionarioLogado, Model model) {
		model.addAttribute("funcionarioLogado", funcionarioLogado);
		model.addAttribute("item", new Item());
		return "itemForm";
	}

	/**
	 * Carrega pagina para listar todos os Itens registrados
	 * 
	 * @param funcionarioLogado
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/itens", method = RequestMethod.GET)
	public String getListaItem(@SessionAttribute("funcionarioLogado") Funcionario funcionarioLogado, Model model) {
		model.addAttribute("funcionarioLogado", funcionarioLogado);
		model.addAttribute("listaItem", itemService.getListaItem());
		return "listaItem";
	}

	/**
	 * Carrega detalhes de um Item pelo seu ID
	 * 
	 * @param funcionarioLogado
	 * @param idItem
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/item/{id}", method = RequestMethod.GET)
	public String getItem(@SessionAttribute("funcionarioLogado") Funcionario funcionarioLogado,
			@PathVariable("id") int idItem, Model model) {
		model.addAttribute("item", itemService.getItemById(idItem));
		model.addAttribute("funcionarioLogado", funcionarioLogado);
		return "itemDetail";
	}

	/**
	 * Remover Item pelo seu ID
	 * 
	 * @param funcionarioLogado
	 * @param idItem
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/item/delete/{id}", method = RequestMethod.GET)
	public String removerFuncionario(@SessionAttribute("funcionarioLogado") Funcionario funcionarioLogado,
			@PathVariable("id") int idItem, Model model) {
		Item item = itemService.getItemById(idItem);
		try {
			itemService.removerItem(funcionarioLogado, item);
		} catch (AutenticacaoException e) {
			model.addAttribute("listaItem", itemService.getListaItem());
			model.addAttribute("mensagem", e.getMessage());
			return "listaItem";
		}
		return "redirect:/itens";
	}

	/**
	 * Atualiza o Item
	 * 
	 * @param funcionarioLogado
	 * @param item
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/item/update", method = RequestMethod.POST)
	public String atualizar(@SessionAttribute("funcionarioLogado") Funcionario funcionarioLogado,
			@ModelAttribute("item") Item item, Model model) {
		try {
			itemService.atualizarItem(funcionarioLogado, item);
		} catch (AutenticacaoException e) {
			model.addAttribute("item", new Item());
			model.addAttribute("funcionarioLogado", funcionarioLogado);
			model.addAttribute("mensagem", e.getMessage());
			return "itemDetail";
		}
		return "redirect:/itens";
	}

	/**
	 * Retornar o item de acordo com usuario digita - Autocomplete Item
	 * 
	 * @param itemTitulo
	 * @return List<Item>
	 */
	@RequestMapping(value = "/getItens", method = RequestMethod.GET)
	public @ResponseBody List<Item> getItem(@RequestParam String itemTitulo) {
		List<Item> resultado = new ArrayList<Item>();
		List<Item> listaAluno = itemService.getListaItem();

		for (Item obj : listaAluno) {
			if (obj.getTitulo().toLowerCase().contains(itemTitulo)) {
				resultado.add(obj);
			}
		}
		return resultado;
	}
}
