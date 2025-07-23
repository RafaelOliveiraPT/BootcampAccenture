package pt.upacademy.jseproject.textinterface.states;

import pt.upacademy.jseproject.model.ShelfService;

public abstract class ShelfState extends State{
	
	protected ShelfService shelfService = new ShelfService();
}
