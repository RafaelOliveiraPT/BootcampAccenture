package pt.upacademy.jseproject.repositories;

import pt.upacademy.jseproject.model.Product;
import pt.upacademy.jseproject.repositories.interfaces.ProductRepositoryCRUD_Interface;

public class ProductRepository extends EntityRepository<Product> implements ProductRepositoryCRUD_Interface{
	private static final ProductRepository INSTANCE = new ProductRepository();

	public static ProductRepository getInstance() {
		return INSTANCE;
	}
	
	private ProductRepository() {}




}
