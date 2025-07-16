package pt.upacademy.jseproject.model;

import pt.upacademy.jseproject.repositories.ProductRepository;
import pt.upacademy.jseproject.repositories.ShelfRepository;
import pt.upacademy.jseproject.utilities.Util;

public class ShelfService {
	private ProductRepository productRepository;
	private ShelfRepository shelfRepository;
	
	public ShelfService() {
		productRepository = ProductRepository.getInstance();
		shelfRepository = ShelfRepository.getInstance();
	}
	
	public void createShelf() {
		Shelf newShelf = new Shelf();
		Product auxProduct;

		if (productRepository.size() != 0) {
			// obter o id para produto
			while (true) {
				auxProduct = productRepository.get(Util.getIntFromKeyboard("Insira o ID do produto:", true, false));
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
		shelfRepository.add(newShelf);
	}

	public void editShelf() {
		Shelf auxPrateleira;

		auxPrateleira = shelfRepository.get(Util.getIntFromKeyboard("Insira o ID do prateleira:", true, false));
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
		if (productRepository.size() != 0) {
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
				auxProduct = productRepository.get(auxInt);
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

	public void consultShelfDetails() {
		int auxInt;
		Shelf auxShelf;

		auxInt = Util.getIntFromKeyboard("Insira o ID da prateleira:", true, false);
		if (auxInt >= 0) {
			auxShelf = shelfRepository.get(auxInt);
			if (auxShelf != null) {
				System.out.println("Prateleira:");
				System.out.println("ID=" + auxShelf.getId());
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

	public void removeShelf() {
		int auxInt;
		Shelf auxShelf;

		auxInt = Util.getIntFromKeyboard("Insira o ID da prateleira:", true, false);
		if (auxInt >= 0) {
			auxShelf = shelfRepository.get(auxInt);
			if (auxShelf != null) {
				if (auxShelf.getProduto() != null) {
					auxShelf.getProduto().removeShelf(auxShelf);
					auxShelf.setProduto(null);
				}
				shelfRepository.remove(auxShelf.getId());
			} else {
				System.out.println("Não existe nenhum produto com esse ID");
			}
		} else {
			System.out.println("O ID tem que ser um número positivo.");
		}
	}

	public void showShelves() {
		System.out.println("Lista das prateleiras existentes:");
		for (Shelf shelf : shelfRepository.getAll()) {
			shelf.showShelf();
		}
	}
	
}