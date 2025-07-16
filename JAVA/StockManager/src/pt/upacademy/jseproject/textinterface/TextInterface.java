package pt.upacademy.jseproject.textinterface;

import java.util.Scanner;

import pt.upacademy.jseproject.model.ProductService;
import pt.upacademy.jseproject.model.ShelfService;
import pt.upacademy.jseproject.utilities.Util;

public class TextInterface {
	
	final static public ProductService productService = new ProductService();
	final static public ShelfService shelfService = new ShelfService();
	
	
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

			choosenOptionByUser = Util.getIntFromKeyboard(menuShelf,true, false);

			switch (choosenOptionByUser) {
			case 1:
				// criar nova prateleira
				shelfService.createShelf();
				break;
			case 2:
				// editar um produto existente
				shelfService.editShelf();
				break;
			case 3:
				// consultar o detalhe de um produto
				shelfService.consultShelfDetails();
				break;
			case 4:
				// remover um produto
				shelfService.removeShelf();
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
					productService.createProduct();
					break;
				case 2:
					// editar um produto existente
					productService.editProduct();
					break;
				case 3:
					// consultar o detalhe de um produto
					productService.consultProductDetails();
					break;
				case 4:
					// remover um produto
					productService.removeProduct();
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
}
