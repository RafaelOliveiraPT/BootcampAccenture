package pt.upacademy.jseproject.repositories;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import pt.upacademy.jseproject.model.MyEntity;
import pt.upacademy.jseproject.repositories.interfaces.EntityRepositoryCRUD_Interface;


public abstract class EntityRepository <T extends MyEntity> implements EntityRepositoryCRUD_Interface<T>{
	protected Map<Long, T> map = new HashMap<Long, T>();
	protected long currentId = 0;

	private long getNextId() {
		return currentId+1;
	}
	
	public T get(long id) {
		return map.get(id);
	}
	
	public Collection<T> getAll(){
		return map.values();
	}
	
	public Long add(T t) {
		long nextId = getNextId();
		t.setId(nextId);
		map.put(nextId, t);
		currentId = nextId;
		return nextId;
	}
	
	public void update(T t) {
		if(map.get(t.getId()) != null) {
			map.put(t.getId(), t);			
		}
	}
	
	public void remove(long id) {
		map.remove(id);
	}
	
	
	public int size() {
		return map.size();
	}
	
}
