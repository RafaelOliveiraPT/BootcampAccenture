package pt.upacademy.jseproject.repositories.interfaces;

import java.util.Collection;

import pt.upacademy.jseproject.model.MyEntity;
import pt.upacademy.jseproject.utilities.interfaces.CRUD_Interface;

public interface EntityRepositoryCRUD_Interface <T extends MyEntity> extends CRUD_Interface<T>{
	
	Collection<T> getAll();
	
	int size();

	
}
