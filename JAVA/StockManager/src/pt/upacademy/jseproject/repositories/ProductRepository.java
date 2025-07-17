package pt.upacademy.jseproject.repositories;

import pt.upacademy.jseproject.model.Product;

public class ProductRepository extends EntityRepository<Product>{
	private static final ProductRepository INSTANCE = new ProductRepository();

	public static ProductRepository getInstance() {
		return INSTANCE;
	}
	
	private ProductRepository() {}
}
