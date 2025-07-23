package pt.upacademy.jseproject.textinterface.states;

import pt.upacademy.jseproject.utilities.Util;

public class RemoveShelfMenu extends ShelfState {

	@Override
	public int on() {
		shelfService.removeShelf(Util.getIntFromKeyboard("Insira o ID da prateleira:", true, false));
		return 1;
	}

}
