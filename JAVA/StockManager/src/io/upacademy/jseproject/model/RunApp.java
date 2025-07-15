package io.upacademy.jseproject.model;

import java.util.ArrayList;
import java.util.Scanner;
import io.upacademy.jseproject.test.SampleData;
import io.upacademy.jseproject.textinterface.Interface;
import io.upacademy.jseproject.utilities.Util;

public class RunApp {
	private ArrayList<Product> productsList = new ArrayList<Product>();
	private ArrayList<Shelf> shelvesList = new ArrayList<Shelf>();
	private Scanner reader = new Scanner(System.in);

	
	
	public ArrayList<Product> getProductsList() {
		return productsList;
	}

	public ArrayList<Shelf> getShelvesList() {
		return shelvesList;
	}

	public Scanner getReader() {
		return reader;
	}

	public static void beginApp() {
		RunApp myApplication = new RunApp();
		loadSampleData(myApplication);
		myApplication.showMenu();
	}

	private static void loadSampleData(RunApp myApplication) {
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
		boolean showMenuOption = true;
		Scanner reader = new Scanner(System.in);
		int choosenOptionByUser = -1;

		do {
			Interface.showMenu();
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
		boolean showMenuOption = true;
		int choosenOptionByUser = -1;

		do {
			System.out.println("-------------------------------------------------------------------------");
			showShelves();
			System.out.println("-------------------------------------------------------------------------");
			Interface.showShelfMenu();

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

				auxProduct = ProductService.getProduct(auxInt, this);
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
				auxProduct = ProductService.getProduct(auxInt, this);
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
		boolean showMenuOption = true;
		int choosenOptionByUser = -1;

		do {
			System.out.println("-------------------------------------------------------------------------");
			ProductService.showProducts(this);
			System.out.println("-------------------------------------------------------------------------");
			Interface.showProductMenu();
			choosenOptionByUser = Util.getIntFromKeyboard(true, false);

			if (choosenOptionByUser >= 1 && choosenOptionByUser <= 5) {
				switch (choosenOptionByUser) {
				case 1:
					// criar novo produto
					ProductService.createProduct(this);
					break;
				case 2:
					// editar um produto existente
					ProductService.editProduct(this);
					break;
				case 3:
					// consultar o detalhe de um produto
					ProductService.consultProductDetails(this);
					break;
				case 4:
					// remover um produto
					ProductService.removeProduct(this);
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

	private void showShelves() {
		System.out.println("Lista das prateleiras existentes:");
		for (Shelf shelf : shelvesList) {
			shelf.showShelf();
		}
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
