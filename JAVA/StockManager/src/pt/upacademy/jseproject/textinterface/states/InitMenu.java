package pt.upacademy.jseproject.textinterface.states;

import pt.upacademy.jseproject.utilities.Util;

public class InitMenu extends State {

	@Override
	public int on() {
		System.out.println("Por favor selecione uma das seguintes opções:\n" + "1) Listar produtos\n"
				+ "2) Listar prateleiras\n" + "3) Sair");
		return Util.getIntFromKeyboard(null, true, false);
	}

}
