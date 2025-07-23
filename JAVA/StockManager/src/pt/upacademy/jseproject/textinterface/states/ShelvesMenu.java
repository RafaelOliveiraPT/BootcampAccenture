package pt.upacademy.jseproject.textinterface.states;

import pt.upacademy.jseproject.model.ShelfService;
import pt.upacademy.jseproject.utilities.Util;

public class ShelvesMenu extends State {
	final private ShelfService shelfService = new ShelfService();

	@Override
	public int on() {
		System.out.println("-------------------------------------------------------------------------");
		shelfService.showShelves();
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("Por favor selecione uma das seguintes opções:\n" + "1) Criar nova prateleira\n"
				+ "2) Editar uma prateleira existente\n" + "3) Consultar o detalhe de uma prateleira\n"
				+ "4) Remover uma prateleira\n" + "5) Voltar ao ecrã anterior"); 
		return Util.getIntFromKeyboard(null, true, false);
	}

}
