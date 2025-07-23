package pt.upacademy.jseproject.textinterface;

import java.util.ArrayList;
import java.util.Scanner;

import pt.upacademy.jseproject.model.Product;
import pt.upacademy.jseproject.model.ProductService;
import pt.upacademy.jseproject.model.Shelf;
import pt.upacademy.jseproject.model.ShelfService;
import pt.upacademy.jseproject.test.SampleData;
import pt.upacademy.jseproject.utilities.Util;

public class TextInterface {

	final private ProductService productService = new ProductService();
	final private ShelfService shelfService = new ShelfService();

	public static void main(String args[]) {
		TextInterfaceStateMachine ti = new TextInterfaceStateMachine();
		// carrega dados dummie
		new SampleData().loadSampleData();
		ti.start();
	}

	public void showMenu() {
		String menu = "Por favor selecione uma das seguintes opções:\n" + "1) Listar produtos\n"
				+ "2) Listar prateleiras\n" + "3) Sair";

		boolean showMenuOption = true;
		Scanner reader = new Scanner(System.in);
		int choosenOptionByUser = -1;
		do {
			choosenOptionByUser = Util.getIntFromKeyboard(menu, true, false);
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

	public void showShelfMenu() {
		String menuShelf = "Por favor selecione uma das seguintes opções:\n" + "1) Criar nova prateleira\n"
				+ "2) Editar uma prateleira existente\n" + "3) Consultar o detalhe de uma prateleira\n"
				+ "4) Remover uma prateleira\n" + "5) Voltar ao ecrã anterior";
		boolean showMenuOption = true;
		int choosenOptionByUser = -1;

		do {
			System.out.println("-------------------------------------------------------------------------");
			shelfService.showShelves();
			System.out.println("-------------------------------------------------------------------------");

			choosenOptionByUser = Util.getIntFromKeyboard(menuShelf, true, false);

			switch (choosenOptionByUser) {
			case 1:
				// criar nova prateleira
				createShelfMenu();
				break;
			case 2:
				// editar um produto existente
				editShelfMenu();
				break;
			case 3:
				// consultar o detalhe de um produto
				consultShelfDetailsMenu();
				break;
			case 4:
				// remover um produto
				removeShelfMenu();
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

	private void createShelfMenu() {
		long productId;
		double diariaAluguer;

		productId = Util.getIntFromKeyboard("Insira o ID do produto:", true, false);
		diariaAluguer = Util.getDoubleFromKeyboard("Introduza um preço para a diária do aluguer:", true, false);
		shelfService.createShelf(productId, diariaAluguer);
	}
	
	private void editShelfMenu() {
		Shelf shelf = shelfService.getShelf(Util.getIntFromKeyboard("Insira o ID do prateleira:", true, false));
		long productId = -1;
		double diariaAluguer;
		double auxDouble;
		if (shelf != null) {
			// se não houver produtos não vale a pena pedir o capacity e o produto
			if (shelfService.existsProducts()) {
				// mostra o produto que esta a ocupar a prateleira
				if (shelf.getProduct() != null) {
					System.out.println("Prateleira está ocupada com o produto=" + shelf.getProduct().getName());
				} else {
					System.out.println("A prateleira está vazia.");
				}
				if (shelf.getProduct() != null) {
					shelf.getProduct().removeShelf(shelf.getId());
				}
				productId = Util.getIntFromKeyboard("Insira o numero do produto (-1 se N/A):", false, false);
			}
			System.out.print("valor atual diariaAluguer=" + shelf.getDiariaAluguer());
			// obter o valor para o atributo diaria de aluguer
			auxDouble = Util.getDoubleFromKeyboard(" Introduza um novo preço:", true, true);
			if(auxDouble >= 0) {
				diariaAluguer = auxDouble;				
			}
			else {
				diariaAluguer = shelf.getDiariaAluguer();
			}
			shelfService.editShelf(shelf.getId(), productId, diariaAluguer);
		} else {
			System.out.println("Não existe nenhuma prateleira com esse id");
		}
	}


	private void consultShelfDetailsMenu() {
		shelfService.consultShelfDetails(Util.getIntFromKeyboard("Insira o ID da prateleira:", true, false));
	}
	
	private void removeShelfMenu() {
		shelfService.removeShelf(Util.getIntFromKeyboard("Insira o ID da prateleira:", true, false));
	}
	
	public void showProductMenu() {
		boolean showMenuOption = true;
		int choosenOptionByUser = -1;
		String menuProduct = "Por favor selecione uma das seguintes opções:\n" + "1) Criar novo produto\n"
				+ "2) Editar um produto existente\n" + "3) Consultar o detalhe de um produto\n"
				+ "4) Remover um produto\n" + "5) Voltar ao ecrã anterior";

		do {
			System.out.println("-------------------------------------------------------------------------");
			productService.showProducts();
			System.out.println("-------------------------------------------------------------------------");
			choosenOptionByUser = Util.getIntFromKeyboard(menuProduct, true, false);

			if (choosenOptionByUser >= 1 && choosenOptionByUser <= 5) {
				switch (choosenOptionByUser) {
				case 1:
					// criar novo produto
					createProductMenu();
					break;
				case 2:
					// editar um produto existente
					editProductMenu();
					break;
				case 3:
					// consultar o detalhe de um produto
					consultProductDetailsMenu();
					break;
				case 4:
					// remover um produto
					removeProductMenu();
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

	private void removeProductMenu() {
		productService.removeProduct(Util.getIntFromKeyboard("Insira o ID do produto:", true, false));
	}

	private void consultProductDetailsMenu() {
		productService.consultProductDetails(Util.getIntFromKeyboard("Insira o ID do produto:", true, false));
	}

	private void createProductMenu() {
		String nameProductString;
		ArrayList<Long> shelvesList = new ArrayList<Long>();
		int auxInt;
		double discount;
		double iva;
		double pvp;

		System.out.println("Insira o nome do produto:");
		nameProductString = Util.getLineFromKeyboard(false);

		// vai ser pedido ao utilizador uma prateleira de cada vez até inserir o valor
		// -1
		while (true) {
			auxInt = Util.getIntFromKeyboard(
					"Insira o numero da prateleira em que o produto está colocado. (-1 p/ terminar!)", false, false);
			shelvesList.add((long) auxInt);
			if (auxInt == -1) {
				break;
			}
		}

		discount = Util.getDoubleFromKeyboard("Insira o valor de desconto do produto:", true, false);
		iva = Util.getDoubleFromKeyboard("Insira o valor do iva do produto:", true, false);
		pvp = Util.getDoubleFromKeyboard("Insira o valor do PVP do produto:", true, false);
		productService.createProduct(nameProductString, shelvesList, discount, iva, pvp);
	}

	private void editProductMenu() {
		int auxInt;
		Product auxProduct;
		String auxStr;
		double auxDouble;
		String name;
		double discount;
		double iva;
		double pvp;
		int productId;
		ArrayList<Long> shelvesList = new ArrayList<Long>();

		productId = Util.getIntFromKeyboard("Insira o ID do produto:", true, false);
		if (productId >= 0) {
			auxProduct = productService.getProduct(productId);
			if (auxProduct != null) {
				// estrutura de editar o produto aqui
				// change product name
				System.out.print("Nome de produto atual: " + auxProduct.getName() + " modificar:");
				// auxStr = reader.nextLine();
				auxStr = Util.getLineFromKeyboard(true);
				if (!auxStr.isEmpty()) {
					name = auxStr;
				} else {
					name = auxProduct.getName();
				}

				// change discount
				auxDouble = Util.getDoubleFromKeyboard(
						"Desconto do produto atual: " + auxProduct.getDiscount() + " modificar:", true, true);
				if (auxDouble >= 0) {
					discount = auxDouble;
				} else {
					discount = auxProduct.getDiscount();
				}

				// change iva
				auxDouble = Util.getDoubleFromKeyboard("IVA do produto atual: " + auxProduct.getIva() + " modificar:",
						true, true);
				if (auxDouble >= 0) {
					iva = auxDouble;
				} else {
					iva = auxProduct.getIva();
				}

				auxDouble = Util.getDoubleFromKeyboard("PVP do produto atual: " + auxProduct.getPvp() + " modificar:",
						true, true);
				if (auxDouble >= 0) {
					pvp = auxDouble;
				} else {
					pvp = auxProduct.getPvp();
				}
				productService.showShelvesFromProduct(productId);
				productService.clearShelves(productId);
				// vai ser pedido ao utilizador uma prateleira de cada vez até inserir o valor
				// -1
				while (true) {
					auxInt = Util.getIntFromKeyboard(
							"Insira o numero da prateleira em que o produto está colocado. (-1 p/ terminar!)", false,
							false);
					if (auxInt == -1) {
						break;
					}
					shelvesList.add((long) auxInt);
				}
				productService.editProduct((long) productId, name, discount, iva, pvp, shelvesList);
			} else {
				System.out.println("Não existe nenhum produto com esse ID");
			}
		} else {
			System.out.println("O ID tem que ser um número positivo.");
		}
	}
}
