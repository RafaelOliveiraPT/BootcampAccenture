package pt.upacademy.jseproject.textinterface.states;

import pt.upacademy.jseproject.model.Shelf;
import pt.upacademy.jseproject.utilities.Util;

public class EditShelfMenu extends ShelfState {

	@Override
	public int on() {
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
		return 1;
	}
}
