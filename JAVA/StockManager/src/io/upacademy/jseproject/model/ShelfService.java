package io.upacademy.jseproject.model;

import java.util.ArrayList;

import io.upacademy.jseproject.utilities.Util;

public class ShelfService {

	public static void createShelf(RunApp app) {
		Shelf newShelf = new Shelf();
		Product auxProduct;
		ArrayList <Shelf> shelvesList = app.getShelvesList();
		ArrayList <Product> productsList = app.getProductsList();

		if (productsList.size() != 0) {
			// obter o id para produto
			while (true) {
				auxProduct = ProductService.getProduct(Util.getIntFromKeyboard("Insira o ID do produto:", true, false), app);
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
		newShelf.setDiariaAluguer(Util.getDoubleFromKeyboard("Introduza um preço para a diária do aluguer:", true, false));
		shelvesList.add(newShelf);
	}

	public static void editShelf(RunApp app) {
		Shelf auxPrateleira;

		auxPrateleira = getShelf(Util.getIntFromKeyboard("Insira o ID do prateleira:", true, false), app);
		if (auxPrateleira != null) {
			editShelftHelper(auxPrateleira, app);
		} else {
			System.out.println("Não existe nenhum produto com esse ID");
		}
	}

	// editar prateleira
	private static void editShelftHelper(Shelf shelf, RunApp app) {
		int auxInt;
		double auxDouble;
		Product auxProduct;
		ArrayList <Product> productsList = app.getProductsList();

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
			while (true) {
				auxInt = Util.getIntFromKeyboard("Insira o numero do produto ou -1 p/ continuar:", false, false);
				if (auxInt == -1) {
					break;
				}
				auxProduct = ProductService.getProduct(auxInt, app);
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

		// obter o valor para o atributo diaria de aluguer
		auxDouble = Util.getDoubleFromKeyboard(" Introduza um novo preço:", true, true);
		if (auxDouble >= 0) {
			shelf.setDiariaAluguer(auxDouble);
		}
	}

	public static void consultShelfDetails(RunApp app) {
		int auxInt;
		Shelf auxShelf;

		auxInt = Util.getIntFromKeyboard("Insira o ID da prateleira:", true, false);
		if (auxInt >= 0) {
			auxShelf = getShelf(auxInt,app);
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

	public static void removeShelf(RunApp app) {
		int auxInt;
		Shelf auxShelf;
		ArrayList <Shelf> shelvesList = app.getShelvesList();

		auxInt = Util.getIntFromKeyboard("Insira o ID da prateleira:", true, false);
		if (auxInt >= 0) {
			auxShelf = getShelf(auxInt, app);
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

	public static void showShelves(RunApp app) {
		ArrayList <Shelf> shelvesList = app.getShelvesList();
		System.out.println("Lista das prateleiras existentes:");
		for (Shelf shelf : shelvesList) {
			shelf.showShelf();
		}
	}
	
	private static Shelf getShelf(int ID, RunApp app) {
		ArrayList<Shelf> shelvesList = app.getShelvesList();
		for (Shelf shelf : shelvesList) {
			if (shelf.getID() == ID) {
				return shelf;
			}
		}
		return null;
	}
}