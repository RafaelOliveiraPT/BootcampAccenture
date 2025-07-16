package pt.upacademy.jseproject.repositories;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import pt.upacademy.jseproject.model.MyEntity;


public abstract class EntityRepository <T extends MyEntity> {
	private Map<Long, T> map = new HashMap<Long, T>();
	private Long currentId = new Long(0);

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
		map.put(currentId, t);
		t.setId(getNextId());
		return ++currentId;
	}
	
	public void update(T t) {
		map.put(t.getId(), t);
	}
	
	public void remove(long id) {
		map.remove(id);
	}
	
	public int size() {
		return map.size();
	}
}
