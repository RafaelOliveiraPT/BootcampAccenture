package pt.upacademy.jseproject.repositories.interfaces;

import pt.upacademy.jseproject.model.Product;

public interface ProductRepositoryCRUD_Interface extends EntityRepositoryCRUD_Interface<Product>{

	public int size();
}
