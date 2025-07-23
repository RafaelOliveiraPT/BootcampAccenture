package pt.upacademy.jseproject.model;

import java.util.ArrayList;
import pt.upacademy.jseproject.repositories.ProductRepository;
import pt.upacademy.jseproject.repositories.ShelfRepository;

public class ProductService {
	private ProductRepository productRepository;
	private ShelfRepository shelfRepository;

	public ProductService() {
		productRepository = ProductRepository.getInstance();
		shelfRepository = ShelfRepository.getInstance();
	}

	public void createProduct(String productName, ArrayList<Long> shelvesList, double discount, double iva,
			double pvp) {
		Product newProduct = new Product();

		newProduct.setName(productName);
		for (Long shelfId : shelvesList) {
			if (shelfRepository.get(shelfId) != null && shelfRepository.get(shelfId).getCapacity() == 1) {

				shelfRepository.get(shelfId).setCapacity(0);
				shelfRepository.get(shelfId).setProduct(newProduct);
				newProduct.addPrateleira(shelfRepository.get(shelfId));
			} else if (shelfRepository.get(shelfId) != null && shelfRepository.get(shelfId).getCapacity() == 0) {
				System.out.println("A prateleira " + shelfId + " que introduziu já está ocupada!");
			} else {
				System.out.println("A prateleira " + shelfId + " que introduziu não é válida!");
			}
		}
		newProduct.setDiscount(discount);
		newProduct.setIva(iva);
		newProduct.setPvp(pvp);
		productRepository.add(newProduct);
	}

	public void editProduct(long productId, String name, double discount, double iva, double pvp,
			ArrayList<Long> shelvesList) {
		Product product = productRepository.get(productId);
		product.setName(name);
		product.setDiscount(discount);
		product.setIva(iva);
		product.setPvp(pvp);

		if (shelfRepository.size() != 0) {
			for (Long shelfId : shelvesList) {
				if (shelfRepository.get(shelfId) != null && shelfRepository.get(shelfId).getCapacity() == 1) {
					shelfRepository.get(shelfId).setCapacity(0);
					shelfRepository.get(shelfId).setProduct(product);
					product.addPrateleira(shelfRepository.get(shelfId));
				} else if (shelfRepository.get(shelfId) != null && shelfRepository.get(shelfId).getCapacity() == 0) {
					System.out.println("A prateleira que introduziu já está ocupada!");
				} else if (shelfRepository.get(shelfId) == null) {
					System.out.println("A prateleira que introduziu não é válida!");
				}
			}
		}
	}

	public void removeProduct(long productId) {
		Product auxProduct;

		auxProduct = productRepository.get(productId);
		if (auxProduct != null) {
			auxProduct.clearShelves();
			productRepository.remove(auxProduct.getId());
			auxProduct = null;
			System.out.println("Produto removido com sucesso!");
		} else {
			System.out.println("Não existe nenhum produto com esse ID");
		}
	}

	public void showProducts() {
		System.out.println("Lista dos produtos existentes:");
		if (productRepository.size() > 0) {
			for (Product product : productRepository.getAll()) {
				product.showProduct();
			}
		}
	}

	public void showShelvesFromProduct(long productId) {
		Product product = productRepository.get(productId);
		System.out.println("-----------------------------------");
		System.out.println("O produto: " + product.getName() + " está nas seguintes prateleiras:");
		for (Shelf shelf : product.getShelves()) {
			System.out.println(shelf.getId());
		}
		System.out.println("-----------------------------------");
	}

	public void consultProductDetails(long productId) {
		Product auxProduct;

		if (productId >= 0) {
			auxProduct = productRepository.get(productId);
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

	public Product getProduct(long productId) {
		return productRepository.get(productId);
	}

	public void clearShelves(long productId) {
		productRepository.get(productId).clearShelves();

	}
}
