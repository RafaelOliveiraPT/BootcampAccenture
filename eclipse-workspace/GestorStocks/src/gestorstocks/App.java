package gestorstocks;

import java.util.ArrayList;
import java.util.Scanner;

import pt.academy.utililities.Util;

public class App {
	private ArrayList<Product> productsList = new ArrayList<Product>();
	private ArrayList<Shelf> shelvesList = new ArrayList<Shelf>();
	private Scanner reader = new Scanner(System.in);

	public static void main(String[] args) {
		beginApp();
	}

	public static void beginApp() {
		App myApplication = new App();
		loadSampleData(myApplication);
		myApplication.showMenu();
	}

	private static void loadSampleData(App myApplication) {
		ArrayList<Product> productsList = SampleData.getSampleDataProducts();
		ArrayList<Shelf> shelvesList = SampleData.getSampleDataShelves();
		
		for (Shelf shelf : shelvesList) {
			myApplication.shelvesList.add(shelf);
		}
		for (Product product : productsList) {
			myApplication.productsList.add(product);
		}
	}

	private void showMenu() {
		String menu = "Por favor selecione uma das seguintes opções:\n" + "1) Listar produtos\n"
				+ "2) Listar prateleiras\n" + "3) Sair";

		boolean showMenuOption = true;
		Scanner reader = new Scanner(System.in);
		int choosenOptionByUser = -1;

		do {
			System.out.println(menu);
			choosenOptionByUser = Util.getIntFromKeyboard(true, false);
			switch (choosenOptionByUser) {
			case 1:
				showProductMenu();
				break;
			case 2:
				showShelfMenu();
				break;
			case 3:
				showMenuOption = false;
				System.out.println("A aplicação de stocks terminou.");
				break;
			default:
				break;
			}

		} while (showMenuOption);
		reader.close();
	}

	private void showShelfMenu() {
		String menuShelf = "Por favor selecione uma das seguintes opções:\n" + "1) Criar nova prateleira\n"
				+ "2) Editar uma prateleira existente\n" + "3) Consultar o detalhe de uma prateleira\n"
				+ "4) Remover uma prateleira\n" + "5) Voltar ao ecrã anterior";

		boolean showMenuOption = true;

		int choosenOptionByUser = -1;

		do {
			System.out.println("-------------------------------------------------------------------------");
			showShelves();
			System.out.println("-------------------------------------------------------------------------");
			System.out.println(menuShelf);

			choosenOptionByUser = Util.getIntFromKeyboard(true, false);

			switch (choosenOptionByUser) {
			case 1:
				// criar nova prateleira
				createShelf();
				break;
			case 2:
				// editar um produto existente
				editShelf();
				break;
			case 3:
				// consultar o detalhe de um produto
				consultShelfDetails();
				break;
			case 4:
				// remover um produto
				removeShelf();
				break;
			case 5:
				// voltar ao ecra anterior
				showMenuOption = false;
				break;
			default:
				break;
			}

		} while (showMenuOption);
	}

	private void createShelf() {
		Shelf newShelf = new Shelf();
		int auxInt;
		double auxDouble;
		Product auxProduct;

		if (productsList.size() != 0) {
			// obter o id para produto
			System.out.print("Insira o ID do produto:");
			while (true) {
				auxInt = Util.getIntFromKeyboard(true, false);

				auxProduct = getProduct(auxInt);
				if (auxProduct != null) {
					newShelf.setProduto(auxProduct);
					newShelf.setCapacity(0);
					auxProduct.addPrateleira(newShelf);
					break;
				} else {
					System.out.println("Não existe nenhum produto com esse ID");
				}
			}
		}

		// obter o valor para o atributo diariaAluguer
		System.out.print("Introduza um preço para a diária do aluguer:");
		auxDouble = Util.getDoubleFromKeyboard(true, false);
		newShelf.setDiariaAluguer(auxDouble);
		shelvesList.add(newShelf);
	}

	private void editShelf() {
		int auxInt;
		Shelf auxPrateleira;

		System.out.print("Insira o ID do prateleira:");
		auxInt = Util.getIntFromKeyboard(true, false);
		auxPrateleira = getShelf(auxInt);
		if (auxPrateleira != null) {
			editShelftHelper(auxPrateleira);
		} else {
			System.out.println("Não existe nenhum produto com esse ID");
		}
	}

	// editar prateleira
	private void editShelftHelper(Shelf shelf) {
		int auxInt;
		double auxDouble;
		Product auxProduct;

		// se não houver produtos não vale a pena pedir o capacity e o produto
		if (productsList.size() != 0) {
			shelf.setCapacity(1);

			// mostra o produto que esta a ocupar a prateleira
			if (shelf.getProduto() != null) {
				System.out.println("Prateleira ocupada com o produto=" + shelf.getProduto().getName());
			} else {
				System.out.println("A prateleira está vazia.");
			}
			if (shelf.getProduto() != null) {
				shelf.getProduto().removeShelf(shelf);
			}
			shelf.setProduto(null);
			System.out.print("Insira o numero do produto ou -1 p/ continuar:");
			while (true) {
				auxInt = Util.getIntFromKeyboard(false, false);
				if (auxInt == -1) {
					break;
				}
				auxProduct = getProduct(auxInt);
				if (auxProduct != null) {
					shelf.setProduto(auxProduct);
					auxProduct.addPrateleira(shelf);
					shelf.setCapacity(0);
					break;
				} else {
					System.out.print("O ID não está associado a nenhum produto, introduza um novo ID:");
				}
			}

		} else {
			System.out.println("capacity=" + shelf.getCapacity());
		}
		System.out.print("valor atual diariaAluguer=" + shelf.getDiariaAluguer());
		System.out.print(" introduza um novo preço:");

		// obter o valor para o atributo diaria de aluguer
		auxDouble = Util.getDoubleFromKeyboard(true, true);
		if (auxDouble >= 0) {
			shelf.setDiariaAluguer(auxDouble);
		}
	}

	private void consultShelfDetails() {
		int auxInt;
		Shelf auxShelf;

		System.out.print("Insira o ID da prateleira:");
		auxInt = Util.getIntFromKeyboard(true, false);
		if (auxInt >= 0) {
			auxShelf = getShelf(auxInt);
			if (auxShelf != null) {
				System.out.println("Prateleira:");
				System.out.println("ID=" + auxShelf.getID());
				System.out.println("Capacity=" + auxShelf.getCapacity());
				if (auxShelf.getCapacity() == 0) {
					System.out.println("A prateleira contêm=" + auxShelf.getProduto().getName());
				} else {
					System.out.println("A prateleira está vazia.");
				}
				System.out.println("Diaria de aluguer=" + auxShelf.getDiariaAluguer());
			} else {
				System.out.println("Não existe nenhum produto com esse ID");
			}
		} else {
			System.out.println("O ID tem que ser um número positivo.");
		}
	}

	private void removeShelf() {
		int auxInt;
		Shelf auxShelf;

		System.out.print("Insira o ID da prateleira:");
		auxInt = Util.getIntFromKeyboard(true, false);
		if (auxInt >= 0) {
			auxShelf = getShelf(auxInt);
			if (auxShelf != null) {
				if (auxShelf.getProduto() != null) {
					auxShelf.getProduto().removeShelf(auxShelf);
					auxShelf.setProduto(null);
				}
				shelvesList.remove(auxShelf);
			} else {
				System.out.println("Não existe nenhum produto com esse ID");
			}
		} else {
			System.out.println("O ID tem que ser um número positivo.");
		}
	}

	private void showProductMenu() {
		String menuProduct = "Por favor selecione uma das seguintes opções:\n" + "1) Criar novo produto\n"
				+ "2) Editar um produto existente\n" + "3) Consultar o detalhe de um produto\n"
				+ "4) Remover um produto\n" + "5) Voltar ao ecrã anterior";

		boolean showMenuOption = true;
		int choosenOptionByUser = -1;

		do {
			System.out.println("-------------------------------------------------------------------------");
			showProducts();
			System.out.println("-------------------------------------------------------------------------");
			System.out.println(menuProduct);

			choosenOptionByUser = Util.getIntFromKeyboard(true, false);

			if (choosenOptionByUser >= 1 && choosenOptionByUser <= 5) {
				switch (choosenOptionByUser) {
				case 1:
					// criar novo produto
					createProduct();
					break;
				case 2:
					// editar um produto existente
					editProduct();
					break;
				case 3:
					// consultar o detalhe de um produto
					consultProductDetails();
					break;
				case 4:
					// remover um produto
					removeProduct();
					break;
				case 5:
					// voltar ao ecra anterior
					showMenuOption = false;
					break;
				default:
					break;
				}
			}

		} while (showMenuOption);
	}

	private void createProduct() {
		Product newProduct = new Product();
		int auxInt;
		double auxDouble;

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

	private void editProduct() {
		int auxInt;
		Product auxProduct;

		System.out.print("Insira o ID do produto:");
		auxInt = Util.getIntFromKeyboard(true, false);
		if (auxInt >= 0) {
			auxProduct = getProduct(auxInt);
			if (auxProduct != null) {
				editProductHelper(auxProduct);
			} else {
				System.out.println("Não existe nenhum produto com esse ID");
			}
		} else {
			System.out.println("O ID tem que ser um número positivo.");
		}
	}

	private void editProductHelper(Product product) {
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

		// change shelves
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

	private void consultProductDetails() {
		int auxInt;
		Product auxProduct;

		System.out.print("Insira o ID do produto:");
		auxInt = Util.getIntFromKeyboard(true, false);
		if (auxInt >= 0) {
			auxProduct = getProduct(auxInt);
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

	private void removeProduct() {
		int auxInt;
		Product auxProduct;

		System.out.print("Insira o ID do produto:");
		auxInt = Util.getIntFromKeyboard(true, false);

		auxProduct = getProduct(auxInt);
		if (auxProduct != null) {
			removeProductHelper(auxProduct);
		} else {
			System.out.println("Não existe nenhum produto com esse ID");
		}
	}

	private void removeProductHelper(Product product) {
		product.clearShelves();
		productsList.remove(product);
		product = null;
		System.out.println("Produto removido com sucesso!");
	}

	private void showShelvesFromProduct(Product product) {
		System.out.println("-----------------------------------");
		System.out.println("O produto: " + product.getName() + " está nas seguintes prateleiras:");
		for (Shelf shelf : product.getPrateleiras()) {
			System.out.println(shelf.getID());
		}
		System.out.println("-----------------------------------");
	}

	private void showProducts() {
		System.out.println("Lista dos produtos existentes:");
		for (Product product : productsList) {
			product.showProduct();
		}
	}

	private void showShelves() {
		System.out.println("Lista das prateleiras existentes:");
		for (Shelf shelf : shelvesList) {
			shelf.showShelf();
		}
	}

	private Product getProduct(int ID) {
		for (Product product : productsList) {
			if (product.getID() == ID)
				return product;
		}
		return null;
	}

	private Shelf getShelf(int ID) {
		for (Shelf shelf : shelvesList) {
			if (shelf.getID() == ID) {
				return shelf;
			}
		}
		return null;
	}
}
