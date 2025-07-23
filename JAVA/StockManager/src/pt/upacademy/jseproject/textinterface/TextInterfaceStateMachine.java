package pt.upacademy.jseproject.textinterface;

import pt.upacademy.jseproject.textinterface.states.CreateProductMenu;
import pt.upacademy.jseproject.textinterface.states.CreateShelfMenu;
import pt.upacademy.jseproject.textinterface.states.RemoveProductMenu;
import pt.upacademy.jseproject.textinterface.states.RemoveShelfMenu;
import pt.upacademy.jseproject.textinterface.states.InitMenu;
import pt.upacademy.jseproject.textinterface.states.ProductsMenu;
import pt.upacademy.jseproject.textinterface.states.ShelvesMenu;
import pt.upacademy.jseproject.test.SampleData;
import pt.upacademy.jseproject.textinterface.states.ConsultProductDetailsMenu;
import pt.upacademy.jseproject.textinterface.states.ConsultShelfDetailsMenu;
import pt.upacademy.jseproject.textinterface.states.State;
import pt.upacademy.jseproject.textinterface.states.EditProductMenu;
import pt.upacademy.jseproject.textinterface.states.EditShelfMenu;

public class TextInterfaceStateMachine {

	public static void main(String args[]) {
		TextInterfaceStateMachine ti = new TextInterfaceStateMachine();
		// carrega dados dummie
		new SampleData().loadSampleData();
		ti.start();
	}

	// 2. states
	private State[] states = { //
			new InitMenu(), // State 0
			new ProductsMenu(), // State 1
			new ShelvesMenu(), // State 2
			new CreateShelfMenu(), // State 3
			new EditShelfMenu(), // State 4
			new ConsultShelfDetailsMenu(), // State 5
			new RemoveShelfMenu(), // State 6
			new CreateProductMenu(), // State 7
			new EditProductMenu(), // State 8
			new ConsultProductDetailsMenu(), // State 9
			new RemoveProductMenu() // State 10
	};
	// 4. transitions
	private int[][] transition = { //
			{ 1, 2 }, // init Menu
			{ 7, 8, 9, 10, 0 }, // Products Menu
			{ 3, 4, 5, 6, 0 }, // Shelves Menu
			{ 2 }, // create shelf menu
			{ 2 }, // edit shelf menu
			{ 2 }, // consult details shelf menu
			{ 2 }, // remove shelf menu
			{ 1 }, // create product menu
			{ 1 }, // edit product menu
			{ 1 }, // consult product details menu
			{ 1 } // remove product menu
	};
	// 3. current
	private int current = 0;

	// 5. All client requests are simply delegated to the current state object
	public void start() {

		while (true) {
			int option = states[current].on();
			if (current == 0 && option == 3) {
				System.out.println("A aplicação terminou!");
				break;
			}
			switch (current) {
			case 0:
				if(option < 1 || option > 3) {
					System.out.println("invalid option!!");
				}else {
					current = transition[current][option - 1];
				}
				break;
			case 1:
				if(option < 1 || option > 5) {
					System.out.println("invalid option!!");
				}else {
					current = transition[current][option - 1];
				}
				break;
			case 2:
				if(option < 1 || option > 5) {
					System.out.println("invalid option!!");
				}else {
					current = transition[current][option - 1];
				}
				break;
			default:
				current = transition[current][option - 1];
				break;
			}
			
		}
	}
}
