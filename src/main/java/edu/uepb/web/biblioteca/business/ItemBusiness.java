package edu.uepb.web.biblioteca.business;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.uepb.web.biblioteca.dao.ItemDAOImpl;
import edu.uepb.web.biblioteca.exception.DAOException;
import edu.uepb.web.biblioteca.model.Item;

/**
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 */
@Service
public class ItemBusiness {
	private ItemDAOImpl itemDAO;
	
	public Item getItem(int idItem) throws DAOException {
		itemDAO = new ItemDAOImpl();
		return itemDAO.get(idItem);
	}
	
	public List<Item> getItens() throws DAOException{
		itemDAO = new ItemDAOImpl();
		return itemDAO.getLista();
	}
}

