package pt.upacademy.jseproject.textinterface.states;

import pt.upacademy.jseproject.model.ProductService;
import pt.upacademy.jseproject.utilities.Util;

public class ProductsMenu extends State {
	final private ProductService productService = new ProductService();

	@Override
	public int on() {
		
		System.out.println("-------------------------------------------------------------------------");
		productService.showProducts();
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("Por favor selecione uma das seguintes opções:\n" + "1) Criar novo produto\n"
				+ "2) Editar um produto existente\n" + "3) Consultar o detalhe de um produto\n"
				+ "4) Remover um produto\n" + "5) Voltar ao ecrã anterior");
		return Util.getIntFromKeyboard(null, true, false);
	}
}
