package io.upacademy.jseproject.textinterface;

public class Interface {

	public static void showMenu() {
		String menu = "Por favor selecione uma das seguintes opções:\n" + "1) Listar produtos\n"
				+ "2) Listar prateleiras\n" + "3) Sair";
		System.out.println(menu);
	}

	public static void showShelfMenu() {
		String menuShelf = "Por favor selecione uma das seguintes opções:\n" + "1) Criar nova prateleira\n"
				+ "2) Editar uma prateleira existente\n" + "3) Consultar o detalhe de uma prateleira\n"
				+ "4) Remover uma prateleira\n" + "5) Voltar ao ecrã anterior";
		System.out.println(menuShelf);
	}
	
	public static void showProductMenu() {
		String menuProduct = "Por favor selecione uma das seguintes opções:\n" + "1) Criar novo produto\n"
				+ "2) Editar um produto existente\n" + "3) Consultar o detalhe de um produto\n"
				+ "4) Remover um produto\n" + "5) Voltar ao ecrã anterior";
			System.out.println(menuProduct);
	}
}
