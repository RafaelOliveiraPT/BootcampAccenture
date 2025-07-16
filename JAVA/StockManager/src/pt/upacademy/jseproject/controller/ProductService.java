package pt.upacademy.jseproject.controller;

import pt.upacademy.jseproject.model.Product;
import pt.upacademy.jseproject.model.Shelf;
import pt.upacademy.jseproject.repositories.ProductRepository;
import pt.upacademy.jseproject.repositories.ShelfRepository;
import pt.upacademy.jseproject.utilities.Util;

public class ProductService {
	private ProductRepository productRepository;
	static ShelfRepository shelfRepository;

	public ProductService() {
		productRepository = ProductRepository.getInstance();
		shelfRepository = ShelfRepository.getInstance();
	}
	
	public void createProduct() {
		Product newProduct = new Product();
		int auxInt;

		System.out.println("Insira o nome do produto:");
		newProduct.setName(Util.getLineFromKeyboard(false));

		// vai ser pedido ao utilizador uma prateleira de cada vez até inserir o valor
		// -1
		while (true) {
			if (shelfRepository.size() == 0) {
				System.out.println("Não existem prateleiras criadas, vamos para o próximo atributo\n");
				break;
			}
			auxInt = Util.getIntFromKeyboard(
					"Insira o numero da prateleira em que o produto está colocado. (-1 p/ terminar!)", false, false);
			if (auxInt == -1) {
				break;
			}

			if (shelfRepository.get(auxInt) != null && shelfRepository.get(auxInt).getCapacity() == 1) {
				shelfRepository.get(auxInt).setCapacity(0);
				shelfRepository.get(auxInt).setProduto(newProduct);
				newProduct.addPrateleira(shelfRepository.get(auxInt));
				break;
			} else if (shelfRepository.get(auxInt) != null && shelfRepository.get(auxInt).getCapacity() == 0) {
				System.out.println("A prateleira que introduziu já está ocupada!");
			} else if (shelfRepository.get(auxInt) == null) {
				System.out.println("A prateleira que introduziu não é válida!");
			}

		}

		newProduct.setDiscount(Util.getDoubleFromKeyboard("Insira o valor de desconto do produto:", true, false));
		newProduct.setIva(Util.getDoubleFromKeyboard("Insira o valor do iva do produto:", true, false));
		newProduct.setPvp(Util.getDoubleFromKeyboard("Insira o valor do PVP do produto:", true, false));
		productRepository.add(newProduct);
	}

	public void editProduct() {
		int auxInt;
		Product auxProduct;

		auxInt = Util.getIntFromKeyboard("Insira o ID do produto:", true, false);
		if (auxInt >= 0) {
			auxProduct = productRepository.get(auxInt);
			if (auxProduct != null) {
				editProductHelper(auxProduct);
			} else {
				System.out.println("Não existe nenhum produto com esse ID");
			}
		} else {
			System.out.println("O ID tem que ser um número positivo.");
		}
	}

	public void editProductHelper(Product product) {
		String auxStr;
		Double auxDouble = 0.0;
		int auxInt;

		// change product name
		System.out.print("Nome de produto atual: " + product.getName() + " modificar:");
		// auxStr = reader.nextLine();
		auxStr = Util.getLineFromKeyboard(true);
		if (!auxStr.isEmpty()) {
			product.setName(auxStr);
			System.out.println("diz que a string tem conteudo");
		}

		// change discount
		auxDouble = Util.getDoubleFromKeyboard("Desconto do produto atual: " + product.getDiscount() + " modificar:",
				true, true);
		if (auxDouble >= 0) {
			product.setDiscount(auxDouble);
		}

		// change iva
		auxDouble = Util.getDoubleFromKeyboard("IVA do produto atual: " + product.getIva() + " modificar:", true, true);
		if (auxDouble >= 0) {
			product.setIva(auxDouble);
		}

		auxDouble = Util.getDoubleFromKeyboard("PVP do produto atual: " + product.getPvp() + " modificar:", true, true);
		if (auxDouble >= 0) {
			product.setPvp(auxDouble);
		}
		showShelvesFromProduct(product);
		product.clearShelves();
		// vai ser pedido ao utilizador uma prateleira de cada vez até inserir o valor
		// -1

		if (shelfRepository.size() == 0) {
			System.out.println("Não existem prateleiras criadas, vamos para o próximo atributo\n");
		} else {
			while (true) {
				auxInt = Util.getIntFromKeyboard(
						"Insira o numero da prateleira em que o produto está colocado. (-1 p/ terminar!)", false,
						false);
				if (auxInt == -1) {
					break;
				}
				if (shelfRepository.get(auxInt) != null && shelfRepository.get(auxInt).getCapacity() == 1) {
					shelfRepository.get(auxInt).setCapacity(0);
					shelfRepository.get(auxInt).setProduto(product);
					product.addPrateleira(shelfRepository.get(auxInt));
					break;
				} else if (shelfRepository.get(auxInt) != null && shelfRepository.get(auxInt).getCapacity() == 0) {
					System.out.println("A prateleira que introduziu já está ocupada!");
				} else if (shelfRepository.get(auxInt) == null) {
					System.out.println("A prateleira que introduziu não é válida!");
				}
			}
		}
	}

	public void removeProduct() {
		int auxInt;
		Product auxProduct;

		auxInt = Util.getIntFromKeyboard("Insira o ID do produto:", true, false);
		auxProduct = productRepository.get(auxInt);
		if (auxProduct != null) {
			removeProductHelper(auxProduct);
		} else {
			System.out.println("Não existe nenhum produto com esse ID");
		}
	}

	private void removeProductHelper(Product product) {
		product.clearShelves();
		productRepository.remove(product.getId());
		product = null;
		System.out.println("Produto removido com sucesso!");
	}

	public void showProducts() {
		System.out.println("Lista dos produtos existentes:");
		if (productRepository.size() > 0) {
			for (Product product : productRepository.getAll()) {
				product.showProduct();
			}
		}
	}

	private static void showShelvesFromProduct(Product product) {
		System.out.println("-----------------------------------");
		System.out.println("O produto: " + product.getName() + " está nas seguintes prateleiras:");
		for (Shelf shelf : product.getShelves()) {
			System.out.println(shelf.getId());
		}
		System.out.println("-----------------------------------");
	}

	public void consultProductDetails() {
		int auxInt;
		Product auxProduct;

		auxInt = Util.getIntFromKeyboard("Insira o ID do produto:", true, false);
		if (auxInt >= 0) {
			auxProduct = productRepository.get(auxInt);
			if (auxProduct != null) {
				System.out.println("-------------------------------------------------------------------------");
				auxProduct.showProduct();
				System.out.println("-------------------------------------------------------------------------");
			} else {
				System.out.println("Não existe nenhum produto com esse ID");
			}
		} else {
			System.out.println("O ID tem que ser um número positivo.");
		}
	}
}
