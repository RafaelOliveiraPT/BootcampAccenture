package io.upacademy.jseproject.model;

import java.util.ArrayList;
import java.util.Scanner;

import io.upacademy.jseproject.utilities.Util;

public class ProductService {

	static void createProduct(RunApp app) {
		Product newProduct = new Product();
		int auxInt;
		double auxDouble;
		ArrayList <Shelf> shelvesList = app.getShelvesList();
		ArrayList <Product> productsList = app.getProductsList();

		System.out.println("Insira o nome do produto:");
		newProduct.setName(Util.getLineFromKeyboard());

		// vai ser pedido ao utilizador uma prateleira de cada vez até inserir o valor
		// -1
		while (true) {
			if (shelvesList.size() == 0) {
				System.out.println("Não existem prateleiras criadas, vamos para o próximo atributo\n");
				break;
			}
			System.out.print("Insira o numero da prateleira em que o produto está colocado. (-1 p/ terminar!)");
			auxInt = Util.getIntFromKeyboard(false, false);
			if (auxInt == -1) {
				break;
			}
			for (int i = 0; i < shelvesList.size(); i++) {
				if (shelvesList.get(i).getID() == auxInt && shelvesList.get(i).getCapacity() == 1) {
					shelvesList.get(i).setCapacity(0);
					shelvesList.get(i).setProduto(newProduct);
					newProduct.addPrateleira(shelvesList.get(i));
					break;
				} else if (shelvesList.get(i).getID() == auxInt && shelvesList.get(i).getCapacity() == 0) {
					System.out.println("A prateleira que introduziu já está ocupada!");
				} else if (i == shelvesList.size() - 1) {
					System.out.println("A prateleira que introduziu não é válida!");
				}
			}
		}

		System.out.print("Insira o valor de desconto do produto:");
		auxDouble = Util.getDoubleFromKeyboard(true, false);
		newProduct.setDiscount(auxDouble);

		System.out.print("Insira o valor do iva do produto:");
		auxDouble = Util.getDoubleFromKeyboard(true, false);
		newProduct.setIva(auxDouble);

		System.out.print("Insira o valor do PVP do produto:");
		auxDouble = Util.getDoubleFromKeyboard(true, false);
		newProduct.setPvp(auxDouble);

		productsList.add(newProduct);
	}
	
	static void editProduct(RunApp app) {
		int auxInt;
		Product auxProduct;

		System.out.print("Insira o ID do produto:");
		auxInt = Util.getIntFromKeyboard(true, false);
		if (auxInt >= 0) {
			auxProduct = getProduct(auxInt, app);
			if (auxProduct != null) {
				editProductHelper(auxProduct, app);
			} else {
				System.out.println("Não existe nenhum produto com esse ID");
			}
		} else {
			System.out.println("O ID tem que ser um número positivo.");
		}
	}
	
	static void editProductHelper(Product product, RunApp app) {
		ArrayList <Shelf> shelvesList = app.getShelvesList();
		ArrayList <Product> productsList = app.getProductsList();
		Scanner reader = app.getReader();
		String auxStr;
		Double auxDouble = 0.0;
		int auxInt;

		// change product name
		System.out.print("Nome de produto atual: " + product.getName() + " modificar:");
		auxStr = reader.nextLine();
		if (!auxStr.isEmpty()) {
			product.setName(auxStr);
			System.out.println("diz que a string tem conteudo");
		}

		// change discount
		System.out.print("Desconto do produto atual: " + product.getDiscount() + " modificar:");
		auxDouble = Util.getDoubleFromKeyboard(true, true);
		if (auxDouble >= 0) {
			product.setDiscount(auxDouble);			
		}

		// change iva
		System.out.print("IVA do produto atual: " + product.getIva() + " modificar:");
		auxDouble = Util.getDoubleFromKeyboard(true, true);
		if (auxDouble >= 0) {			
			product.setIva(auxDouble);
		}

		System.out.print("PVP do produto atual: " + product.getPvp() + " modificar:");
		auxDouble = Util.getDoubleFromKeyboard(true, true);
		if (auxDouble >= 0) {			
			product.setPvp(auxDouble);
		}
		showShelvesFromProduct(product);
		product.clearShelves();
		// vai ser pedido ao utilizador uma prateleira de cada vez até inserir o valor
		// -1
		while (true) {
			if (shelvesList.size() == 0) {
				System.out.println("Não existem prateleiras criadas, vamos para o próximo atributo\n");
				break;
			}
			System.out.print("Insira o numero da prateleira em que o produto está colocado. (-1 p/ terminar!)");
			auxInt = Util.getIntFromKeyboard(false, false);
			if (auxInt == -1) {
				break;
			}
			for (int i = 0; i < shelvesList.size(); i++) {
				if (shelvesList.get(i).getID() == auxInt && shelvesList.get(i).getCapacity() == 1) {
					shelvesList.get(i).setCapacity(0);
					shelvesList.get(i).setProduto(product);
					product.addPrateleira(shelvesList.get(i));
					break;
				} else if (shelvesList.get(i).getID() == auxInt && shelvesList.get(i).getCapacity() == 0) {
					System.out.println("A prateleira que introduziu já está ocupada!");
				} else if (i == shelvesList.size() - 1) {
					System.out.println("A prateleira que introduziu não é válida!");
				}
			}
		}
	}
	
	static void consultProductDetails(RunApp app) {
		int auxInt;
		Product auxProduct;

		System.out.print("Insira o ID do produto:");
		auxInt = Util.getIntFromKeyboard(true, false);
		if (auxInt >= 0) {
			auxProduct = getProduct(auxInt, app);
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

	static void removeProduct(RunApp app) {
		int auxInt;
		Product auxProduct;

		System.out.print("Insira o ID do produto:");
		auxInt = Util.getIntFromKeyboard(true, false);
		auxProduct = getProduct(auxInt, app);
		if (auxProduct != null) {
			removeProductHelper(auxProduct, app);
		} else {
			System.out.println("Não existe nenhum produto com esse ID");
		}
	}

	private static void removeProductHelper(Product product, RunApp app) {
		ArrayList<Product> productsList = app.getProductsList();
		product.clearShelves();
		productsList.remove(product);
		product = null;
		System.out.println("Produto removido com sucesso!");
	}
	
	static Product getProduct(int ID, RunApp app) {
		ArrayList<Product> productsList = app.getProductsList();
		for (Product product : productsList) {
			if (product.getID() == ID)
				return product;
		}
		return null;
	}
	
	static void showProducts(RunApp app) {
		ArrayList<Product> productsList = app.getProductsList();
		System.out.println("Lista dos produtos existentes:");
		for (Product product : productsList) {
			product.showProduct();
		}
	}
	
	private static void showShelvesFromProduct(Product product) {
		System.out.println("-----------------------------------");
		System.out.println("O produto: " + product.getName() + " está nas seguintes prateleiras:");
		for (Shelf shelf : product.getShelves()) {
			System.out.println(shelf.getID());
		}
		System.out.println("-----------------------------------");
	}
}
