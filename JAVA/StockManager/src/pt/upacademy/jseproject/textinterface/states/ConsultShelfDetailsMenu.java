package pt.upacademy.jseproject.textinterface.states;

import pt.upacademy.jseproject.utilities.Util;

public class ConsultShelfDetailsMenu extends ShelfState {
	
	@Override
	public int on() {
		shelfService.consultShelfDetails(Util.getIntFromKeyboard("Insira o ID da prateleira:", true, false));
		return 1;
	}

}
