package pt.upacademy.jseproject.textinterface.states;

import pt.upacademy.jseproject.utilities.Util;

public class RemoveProductMenu extends ProductState {

	@Override
	public int on() {
		productService.removeProduct(Util.getIntFromKeyboard("Insira o ID do produto:", true, false));
		return 1;
	}

}
