package io.upacademy.jseproject.textinterface;

import java.util.Scanner;

import io.upacademy.jseproject.model.ProductService;
import io.upacademy.jseproject.model.RunApp;
import io.upacademy.jseproject.model.ShelfService;
import io.upacademy.jseproject.utilities.Util;

public class TextInterface {

	public static void showMenu(RunApp app) {
		String menu = "Por favor selecione uma das seguintes opções:\n" + "1) Listar produtos\n"
				+ "2) Listar prateleiras\n" + "3) Sair";

		boolean showMenuOption = true;
		Scanner reader = new Scanner(System.in);
		int choosenOptionByUser = -1;
		do {
			choosenOptionByUser = Util.getIntFromKeyboard(menu, true, false);
			switch (choosenOptionByUser) {
			case 1:
				TextInterface.showProductMenu(app);
				break;
			case 2:
				TextInterface.showShelfMenu(app);
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

	public static void showShelfMenu(RunApp app) {
		String menuShelf = "Por favor selecione uma das seguintes opções:\n" + "1) Criar nova prateleira\n"
				+ "2) Editar uma prateleira existente\n" + "3) Consultar o detalhe de uma prateleira\n"
				+ "4) Remover uma prateleira\n" + "5) Voltar ao ecrã anterior";
		boolean showMenuOption = true;
		int choosenOptionByUser = -1;

		do {
			System.out.println("-------------------------------------------------------------------------");
			ShelfService.showShelves(app);
			System.out.println("-------------------------------------------------------------------------");
			System.out.println(menuShelf);

			choosenOptionByUser = Util.getIntFromKeyboard(menuShelf,true, false);

			switch (choosenOptionByUser) {
			case 1:
				// criar nova prateleira
				ShelfService.createShelf(app);
				break;
			case 2:
				// editar um produto existente
				ShelfService.editShelf(app);
				break;
			case 3:
				// consultar o detalhe de um produto
				ShelfService.consultShelfDetails(app);
				break;
			case 4:
				// remover um produto
				ShelfService.removeShelf(app);
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

	public static void showProductMenu(RunApp app) {
		boolean showMenuOption = true;
		int choosenOptionByUser = -1;
		String menuProduct = "Por favor selecione uma das seguintes opções:\n" + "1) Criar novo produto\n"
				+ "2) Editar um produto existente\n" + "3) Consultar o detalhe de um produto\n"
				+ "4) Remover um produto\n" + "5) Voltar ao ecrã anterior";

		do {
			System.out.println("-------------------------------------------------------------------------");
			ProductService.showProducts(app);
			System.out.println("-------------------------------------------------------------------------");
			choosenOptionByUser = Util.getIntFromKeyboard(menuProduct, true, false);

			if (choosenOptionByUser >= 1 && choosenOptionByUser <= 5) {
				switch (choosenOptionByUser) {
				case 1:
					// criar novo produto
					ProductService.createProduct(app);
					break;
				case 2:
					// editar um produto existente
					ProductService.editProduct(app);
					break;
				case 3:
					// consultar o detalhe de um produto
					ProductService.consultProductDetails(app);
					break;
				case 4:
					// remover um produto
					ProductService.removeProduct(app);
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
