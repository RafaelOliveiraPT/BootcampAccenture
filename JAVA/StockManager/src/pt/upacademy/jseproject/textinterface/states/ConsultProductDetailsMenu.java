package pt.upacademy.jseproject.textinterface.states;

import pt.upacademy.jseproject.utilities.Util;

public class ConsultProductDetailsMenu extends ProductState {
			
	@Override
	public int on() {
		productService.consultProductDetails(Util.getIntFromKeyboard("Insira o ID do produto:", true, false));
		return 1;
	}

}
