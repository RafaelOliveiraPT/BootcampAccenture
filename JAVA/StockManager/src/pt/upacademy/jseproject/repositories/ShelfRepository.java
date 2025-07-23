package pt.upacademy.jseproject.repositories;

import pt.upacademy.jseproject.model.Shelf;
import pt.upacademy.jseproject.repositories.interfaces.ShelfRepositoryCRUD_Interface;

public class ShelfRepository extends EntityRepository<Shelf> implements ShelfRepositoryCRUD_Interface{
	private static final ShelfRepository INSTANCE = new ShelfRepository();
	
	public static ShelfRepository getInstance() {
		return INSTANCE;
	}
	
	private ShelfRepository() {
		
	}
}
