package pt.upacademy.jseproject.utilities.interfaces;

import pt.upacademy.jseproject.model.MyEntity;

public abstract interface CRUD_Interface <T extends MyEntity>{
	
	T get(long id);
	
	Long add(T t);
	
	void update(T t);
	
	void remove(long id);

}
