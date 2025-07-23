package pt.upacademy.jseproject.textinterface.states;

import java.util.ArrayList;

import pt.upacademy.jseproject.model.Product;
import pt.upacademy.jseproject.utilities.Util;

public class EditProductMenu extends ProductState {

	@Override
	public int on() {
		int auxInt;
		Product auxProduct;
		String auxStr;
		double auxDouble;
		String name;
		double discount;
		double iva;
		double pvp;
		int productId;
		ArrayList<Long> shelvesList = new ArrayList<Long>();

		productId = Util.getIntFromKeyboard("Insira o ID do produto:", true, false);
		if (productId >= 0) {
			auxProduct = productService.getProduct(productId);
			if (auxProduct != null) {
				// estrutura de editar o produto aqui
				// change product name
				System.out.print("Nome de produto atual: " + auxProduct.getName() + " modificar:");
				// auxStr = reader.nextLine();
				auxStr = Util.getLineFromKeyboard(true);
				if (!auxStr.isEmpty()) {
					name = auxStr;
				} else {
					name = auxProduct.getName();
				}

				// change discount
				auxDouble = Util.getDoubleFromKeyboard(
						"Desconto do produto atual: " + auxProduct.getDiscount() + " modificar:", true, true);
				if (auxDouble >= 0) {
					discount = auxDouble;
				} else {
					discount = auxProduct.getDiscount();
				}

				// change iva
				auxDouble = Util.getDoubleFromKeyboard("IVA do produto atual: " + auxProduct.getIva() + " modificar:",
						true, true);
				if (auxDouble >= 0) {
					iva = auxDouble;
				} else {
					iva = auxProduct.getIva();
				}

				auxDouble = Util.getDoubleFromKeyboard("PVP do produto atual: " + auxProduct.getPvp() + " modificar:",
						true, true);
				if (auxDouble >= 0) {
					pvp = auxDouble;
				} else {
					pvp = auxProduct.getPvp();
				}
				productService.showShelvesFromProduct(productId);
				productService.clearShelves(productId);
				// vai ser pedido ao utilizador uma prateleira de cada vez até inserir o valor
				// -1
				while (true) {
					auxInt = Util.getIntFromKeyboard(
							"Insira o numero da prateleira em que o produto está colocado. (-1 p/ terminar!)", false,
							false);
					if (auxInt == -1) {
						break;
					}
					shelvesList.add((long) auxInt);
				}
				productService.editProduct((long) productId, name, discount, iva, pvp, shelvesList);
			} else {
				System.out.println("Não existe nenhum produto com esse ID");
			}
		} else {
			System.out.println("O ID tem que ser um número positivo.");
		}
		return 1;
	}
}
