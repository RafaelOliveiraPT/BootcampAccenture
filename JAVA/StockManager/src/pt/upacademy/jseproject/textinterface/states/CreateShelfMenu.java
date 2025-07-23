package pt.upacademy.jseproject.textinterface.states;

import pt.upacademy.jseproject.utilities.Util;

public class CreateShelfMenu extends ShelfState {

	@Override
	public int on() {
		long productId;
		double diariaAluguer;

		productId = Util.getIntFromKeyboard("Insira o ID do produto:", true, false);
		diariaAluguer = Util.getDoubleFromKeyboard("Introduza um preço para a diária do aluguer:", true, false);
		shelfService.createShelf(productId, diariaAluguer);
		return 1;
	}

}
