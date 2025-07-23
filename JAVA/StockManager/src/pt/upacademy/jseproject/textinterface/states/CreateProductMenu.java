package pt.upacademy.jseproject.textinterface.states;

import java.util.ArrayList;

import pt.upacademy.jseproject.utilities.Util;

public class CreateProductMenu extends ProductState {
	
	@Override
	public int on() {
		String nameProductString;
		ArrayList<Long> shelvesList = new ArrayList<Long>();
		int auxInt;
		double discount;
		double iva;
		double pvp;

		System.out.println("Insira o nome do produto:");
		nameProductString = Util.getLineFromKeyboard(false);

		// vai ser pedido ao utilizador uma prateleira de cada vez até inserir o valor
		// -1
		while (true) {
			auxInt = Util.getIntFromKeyboard(
					"Insira o numero da prateleira em que o produto está colocado. (-1 p/ terminar!)", false, false);
			shelvesList.add((long) auxInt);
			if (auxInt == -1) {
				break;
			}
		}

		discount = Util.getDoubleFromKeyboard("Insira o valor de desconto do produto:", true, false);
		iva = Util.getDoubleFromKeyboard("Insira o valor do iva do produto:", true, false);
		pvp = Util.getDoubleFromKeyboard("Insira o valor do PVP do produto:", true, false);
		productService.createProduct(nameProductString, shelvesList, discount, iva, pvp);
		return 1;
	}

}
