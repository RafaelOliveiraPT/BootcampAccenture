package pt.upacademy.jseproject.repositories;

import pt.upacademy.jseproject.model.Shelf;

public class ShelfRepository extends EntityRepository<Shelf> {
	private static final ShelfRepository INSTANCE = new ShelfRepository();
	
	public static ShelfRepository getInstance() {
		return INSTANCE;
	}
	
	private ShelfRepository() {
		
	}
}
