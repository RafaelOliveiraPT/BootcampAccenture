package pt.upacademy.jseproject.textinterface.states;

import pt.upacademy.jseproject.model.ProductService;

public abstract class ProductState extends State{
	protected final static ProductService productService = new ProductService();
}
