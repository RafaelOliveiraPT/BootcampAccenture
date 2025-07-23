package pt.upacademy.jseproject.model;

import pt.upacademy.jseproject.repositories.ProductRepository;
import pt.upacademy.jseproject.repositories.ShelfRepository;

public class ShelfService {
	private ProductRepository productRepository;
	private ShelfRepository shelfRepository;

	public ShelfService() {
		productRepository = ProductRepository.getInstance();
		shelfRepository = ShelfRepository.getInstance();
	}

	public void createShelf(long productId, double diariaAluguer) {
		Shelf newShelf = new Shelf();
		Product auxProduct;

		auxProduct = productRepository.get(productId);
		if (auxProduct != null) {
			newShelf.setProduct(auxProduct);
			newShelf.setCapacity(0);
			auxProduct.addPrateleira(newShelf);
		} else {
			System.out.println("Não existe nenhum produto com esse ID");
		}
		// obter o valor para o atributo diariaAluguer
		newShelf.setDiariaAluguer(diariaAluguer);
		shelfRepository.add(newShelf);
	}

	public void editShelf(long shelfId, long productId, double diariaAluguer) {
		Shelf shelf = shelfRepository.get(shelfId);
		Product auxProduct;
		shelf.setCapacity(1);
		shelf.setProduct(null);
		auxProduct = productRepository.get(productId);
		if (auxProduct != null) {
			shelf.setProduct(auxProduct);
			auxProduct.addPrateleira(shelf);
			shelf.setCapacity(0);
		} else {
			System.out.print("O ID não está associado a nenhum produto!");
		}
		shelf.setDiariaAluguer(diariaAluguer);
	}

	
	public void consultShelfDetails(long shelfId) {
		Shelf auxShelf;
		
		if (shelfId >= 0) {
			auxShelf = shelfRepository.get(shelfId);
			if (auxShelf != null) {
				System.out.println("Prateleira:");
				System.out.println("ID=" + auxShelf.getId());
				if (auxShelf.getCapacity() == 0) {
					System.out.println("A prateleira contêm=" + auxShelf.getProduct().getName());
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

	public void removeShelf(long shelfId) {
		Shelf auxShelf;

		if (shelfId >= 0) {
			auxShelf = shelfRepository.get(shelfId);
			if (auxShelf != null) {
				if (auxShelf.getProduct() != null) {
					auxShelf.getProduct().removeShelf(auxShelf.getId());
					auxShelf.setProduct(null);
				}
				shelfRepository.remove(auxShelf.getId());
			} else {
				System.out.println("Não existe nenhuma shelf com esse ID");
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
	
	public Shelf getShelf(long shelfId) {
		return shelfRepository.get(shelfId);
	}
	
	public boolean existsProducts() {
		if(productRepository.size() > 0) {
			return true;
		}else {
			return false;
		}
	}
	
	public void removeProductOfShelf(long shelfId) {
		Shelf shelf = shelfRepository.get(shelfId);
		shelf.setCapacity(1);
		shelf.getProduct().removeShelf(shelfId);
	}
}